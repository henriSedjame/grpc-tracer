package io.github.hsedjame.grpc.tracing.services

import io.github.hsedjame.grpc.tracing.Storage
import io.github.hsedjame.grpc.tracing.Trace
import io.github.hsedjame.grpc.tracing.dtos.*
import io.github.hsedjame.grpc.tracing.handleResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Duration
import java.util.*

class DashboardServiceImpl(val storage: Storage): DashboardServiceGrpcKt.DashboardServiceCoroutineImplBase() {

    override fun getTraces(request: Empty): Flow<TraceInfo> = flow {
       storage.findAll().forEach {
           emit(TraceInfo.newBuilder()
               .setCorrelationId(it.correlationId.toString())
               .setLabel(it.name)
               .build())
       }
    }

    override fun listen(requests: Flow<TraceRequestEvent>): Flow<TraceResponseEvent> = flow {
        requests.collect{ event ->
            handleResult(
                { storage.findById(UUID.fromString(event.correlationId)) },
                {
                    TraceDto.newBuilder()
                        .setCorrelationId(event.correlationId)
                        .setLabel(it.name)
                        .setDuration(it.lifetime.duration().toInt())
                        .setClosed(it.closed)
                        .addAllSpans(
                            it.spans.map { span ->
                                TraceDto.SpanDto.newBuilder()
                                    .setLabel(span.name)
                                    .setStart(Duration.between(it.lifetime.start, span.lifetime.start).toMillis().toInt())
                                    .setDuration(span.lifetime.duration().toInt())
                                    .addAllLogs(span.logs)
                                    .build()
                            }
                        )
                        .build()
                }
            ).also { emit(TraceResponseEvent.newBuilder().setContent(it).build()) }
        }
    }
}