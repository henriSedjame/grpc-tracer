package io.github.hsedjame.grpc.tracing.services

import io.github.hsedjame.grpc.tracing.Trace
import io.github.hsedjame.grpc.tracing.dtos.Empty
import io.github.hsedjame.grpc.tracing.dtos.TraceDto
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asFlow
import reactor.core.publisher.Sinks
import java.time.Duration

class DashboardServiceImpl(private val sinks: Sinks.Many<Trace>): DashboardServiceGrpcKt.DashboardServiceCoroutineImplBase() {

     override fun getTraces(request: Empty): Flow<TraceDto> =
          sinks.asFlux().map {
              TraceDto.newBuilder()
                  .setCorrelationId(it.correlationId.toString())
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
          }.asFlow()

    //override fun getTraces(request: Empty): Flow<TraceDto> = flow {
    //    for (i in 0 .. 10) {
    //        coroutineScope {
    //            delay(500)
    //            emit(TraceDto.newBuilder().setCorrelationId(i.toString()).build())
    //        }
    //    }
    //}

}