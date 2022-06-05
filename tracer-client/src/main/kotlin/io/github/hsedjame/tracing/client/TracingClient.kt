package io.github.hsedjame.tracing.client

import io.github.hsedjame.grpc.tracing.dtos.*
import io.github.hsedjame.grpc.tracing.models.Log
import io.github.hsedjame.grpc.tracing.models.Log.LogLevel
import io.github.hsedjame.grpc.tracing.services.TracingServicesGrpcKt
import io.grpc.ManagedChannel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow
import reactor.core.publisher.Sinks
import java.util.*

class TracingClient(
    channel: ManagedChannel
) {

    companion object {
        val spanLogSinks: HashMap<String, Sinks.Many<LogEvent>> = HashMap()
        val currentTraces: HashMap<String, UUID> = HashMap()
    }

    inner class TracingSpan(private val name: String) {

        fun info(message: String) {
            this@TracingClient.log(
                name,
                Log.newBuilder()
                    .setLevel(LogLevel.INFO)
                    .setMessage(message)
                    .build()
            )
        }

        fun warning(message: String) {
            this@TracingClient.log(
                name,
                Log.newBuilder()
                    .setLevel(LogLevel.WARNING)
                    .setMessage(message)
                    .build()
            )
        }

        fun error(message: String) {
            this@TracingClient.log(
                name,
                Log.newBuilder()
                    .setLevel(LogLevel.ERROR)
                    .setMessage(message)
                    .build()
            )
        }
    }

    private val stub: TracingServicesGrpcKt.TracingServicesCoroutineStub =
        TracingServicesGrpcKt.TracingServicesCoroutineStub(channel)

    suspend fun createTrace(name: String): UUID {
        val response = stub.init(InitTraceRequest.newBuilder().setName(name).build())

        val id = UUID.fromString(response.correlationId)

        currentTraces[Thread.currentThread().name] = id

        println("Trace created with id : ${response.correlationId} for $name")

        return id
    }

    fun registerTrace(id: UUID): Unit {
        currentTraces[Thread.currentThread().name] = id
    }

    fun currentTrace() = currentTraces[Thread.currentThread().name]

    suspend fun closeTrace(id: UUID): Unit {
        val response = stub.close(
            CloseTraceRequest.newBuilder()
                .setCorrelationId(id.toString())
                .build()
        )

        if (response.closed) {
            currentTraces.remove(Thread.currentThread().name)
        }

        println("Trace with id $id closed ::: ${response.closed}")
    }

    suspend fun <T> span(name: String, action: suspend (TracingSpan) -> T): T = coroutineScope {

        val id = currentTraces[Thread.currentThread().name]

        var sink: Sinks.Many<LogEvent>? = null

        var logsJob: Job? = null;

        val response = stub.addSpan(
            AddSpanRequest.newBuilder()
                .setName(name)
                .setCorrelationId(id.toString())
                .build()
        )

        println("Span added with name : $name ::: ${response.added}")

        if (response.added) {
            sink = Sinks.many().replay().all()
            spanLogSinks[name] = sink

            logsJob = launch {
                stub.logEvents(sink!!.asFlux().asFlow())
            }

        }

        val t = action(TracingSpan(name))

        val close = stub.closeSpan(
            CloseSpanRequest.newBuilder()
                .setCorrelationId(id.toString())
                .setSpanName(name)
                .build()
        )

        println("Span with name : $name closed ::: ${close.closed}")

        if (close.closed) {
            sink?.tryEmitComplete()
            spanLogSinks.remove(name)
        }

        logsJob?.cancel()

        t
    }

    private fun log(name: String, log: Log) {
        val id = currentTraces[Thread.currentThread().name]
        spanLogSinks[name]?.tryEmitNext(
            LogEvent.newBuilder()
                .setCorrelationId(id.toString())
                .setSpanName(name)
                .setLog(log)
                .build()
        )

        println("New log emitted in span named $name with correlation id $id")
    }
}