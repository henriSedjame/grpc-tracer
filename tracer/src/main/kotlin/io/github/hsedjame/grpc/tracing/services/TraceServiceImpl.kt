package io.github.hsedjame.grpc.tracing.services

import io.github.hsedjame.grpc.tracing.*
import io.github.hsedjame.grpc.tracing.dtos.*
import io.github.hsedjame.grpc.tracing.models.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.*

class TraceServiceImpl(private val storage: Storage) : TracingServicesGrpcKt.TracingServicesCoroutineImplBase() {

    override suspend fun init(request: InitTraceRequest): InitTraceResponse {
        // Create new trace
        val trace = Trace(UUID.randomUUID(), request.name)

        // Try to store the new trace
        return handleResult(
            { storage.save(trace) },
            {
                InitTraceResponse
                    .newBuilder()
                    .setCorrelationId(it.correlationId.toString())
                    .build()
            }
        )
    }

    override suspend fun addSpan(request: AddSpanRequest): AddSpanResponse {


        // get trace
        return handleResult(
            {
                storage.findById(UUID.fromString(request.correlationId))
            },
            { trace ->

                val spans = trace.spans

                val traceCopy: Pair<Boolean, Trace> = request.name.let { name ->
                    with (spans.find { span -> span.name == name })
                    {
                        this?.let {
                            false to trace
                        } ?: (true to updateSpans(trace) {
                            val list = mutableListOf<Span>()
                            list.addAll(it)
                            list.add(Span(name))
                            list
                        })
                    }
                 }

                if(traceCopy.first) handleResult(
                    {
                        storage.update(traceCopy.second)
                    },
                    {
                        AddSpanResponse.newBuilder().setAdded(true).build()
                    }
                ) else AddSpanResponse.newBuilder().setAdded(false).build()
            }
        )

    }

    override suspend fun closeSpan(request: CloseSpanRequest): CloseSpanResponse {

        return handleResult(
            {
                storage.findById(UUID.fromString(request.correlationId))
            },
            { trace ->
                updateSpans(trace) {

                    handleFaillible(ErrorMessages.SPAN_NOT_FOUND){
                        requireNotNull { it.find { span -> span.name == request.spanName } }
                    }.let { span ->
                        val spanCopy = span.copy(closed = true, lifetime = Lifetime.end(span.lifetime))
                        it.map { s -> if (s.name == request.spanName) spanCopy else s }
                    }

                }.let {

                    handleResult(
                        { storage.update(it) },
                        { CloseSpanResponse.newBuilder().setClosed(true).build() }
                    )

                }
            }
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun logEvents(requests: Flow<LogEvent>): Empty {

        requests.collect { logEvent ->
            GlobalScope.launch {
                println("Receive log ${logEvent.spanName} ::: ${logEvent.log.message}")

                handleResult(
                    {
                        storage.findById(UUID.fromString(logEvent.correlationId))
                    },
                    {trace ->
                        updateSpans(trace) {
                            handleFaillible(ErrorMessages.SPAN_NOT_FOUND){
                                requireNotNull { it.find { span -> span.name == logEvent.spanName } }
                            }.let { span ->
                                val logs = mutableListOf<Log>()
                                logs.addAll(span.logs)
                                logs.add(logEvent.log)
                                val spanCopy = span.copy(logs = logs)
                                it.map { s -> if (s.name == logEvent.spanName) spanCopy else s }
                            }
                        }.let {
                            handleResult(
                                { storage.update(it) },
                                {}
                            )
                        }
                    }
                )
            }

        }

        return Empty.newBuilder().build()
    }

    override suspend fun close(request: CloseTraceRequest): CloseTraceResponse {
        return handleResult(
            {
                storage.findById(UUID.fromString(request.correlationId))
            },
            {
                handleResult(
                    {
                        storage.update(it.copy(closed = true, lifetime = Lifetime.end(it.lifetime)))
                    },
                    {
                        lookup(it)
                        CloseTraceResponse.newBuilder().setClosed(true).build()
                    }
                )
            }
        )
    }

    private fun lookup(trace: Trace) {
        println("_________________________________________________________________")
        println("Trace ::: ${trace.correlationId} ::: ${trace.name}")
        println("Life duration  = ${trace.lifetime.duration()} milliseconds")
        trace.spans.forEach { span ->
            println("Span ::: ${span.name} with ${span.logs.size} logs")
            span.logs.forEach { log ->
                println("Log ::: ${log.level} ::: ${log.message}")
            }
            println(
                "Life duration  = ${
                    Duration.between(span.lifetime.start, span.lifetime.end).toMillis()
                } milliseconds"
            )
        }
    }

}