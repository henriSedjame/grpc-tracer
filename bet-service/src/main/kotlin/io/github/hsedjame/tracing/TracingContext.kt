package io.github.hsedjame.tracing

class TracingContext(val client: TracingClient) {

    suspend fun <T> span(name: String, action: suspend (TracingClient.TracingSpan) -> T): T = client.span(name, action)

}