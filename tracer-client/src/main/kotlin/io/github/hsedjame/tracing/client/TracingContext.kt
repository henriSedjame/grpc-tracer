package io.github.hsedjame.tracing.client

import io.grpc.ManagedChannelBuilder
import java.util.UUID

class TracingContext(host: String, port: Int, val app: String) {

    val client: TracingClient
    init {
        val channel = ManagedChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .build();
        client = TracingClient(channel)

    }
    suspend fun <T> span(name: String, action: suspend (TracingClient.TracingSpan) -> T): T = client.span(name, action)

    fun traceId(): UUID? = client.currentTrace()
}