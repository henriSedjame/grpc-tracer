package io.github.hsedjame.tracing.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import java.util.*

const val CORRELATION_ID = "correlation-id"
const val TRACE_CHILD = "trace-child"

fun createTracingPlugin(context: TracingContext) : ApplicationPlugin<TracingClient> {

    val key = AttributeKey<UUID>(CORRELATION_ID)

    return createApplicationPlugin("tracingPlugin", { context.client }){

        onCall {
            val headers = it.request.headers
            if (headers.contains(TRACE_CHILD)) headers[CORRELATION_ID].also { id ->
                run {
                    val traceId = UUID.fromString(id)
                    pluginConfig.registerTrace(traceId)
                    it.attributes.put(key, traceId)
                }
            }
            else pluginConfig.createTrace("${context.app} ::: ${it.request.httpMethod.value} ${it.request.uri}")
                .also { id ->  it.attributes.put(key, id)}

        }

        onCallRespond { call, _ ->
            val headers = call.request.headers

            if (!headers.contains(TRACE_CHILD)) {
                val task = object : TimerTask() {
                    override fun run() {
                        val id = call.attributes[key]
                        runBlocking {
                            pluginConfig.closeTrace(id)
                        }
                    }
                }

                Timer().schedule(task, 10)
            }
        }
    }
}

fun httpClient(context: TracingContext): HttpClient {
    val client = HttpClient(CIO){
        install(ContentNegotiation) {
            gson {  }
        }
    }
    client.plugin(HttpSend).intercept { request  ->
        context.traceId()?.let {
            request.headers.append(CORRELATION_ID, it.toString())
            request.headers.append(TRACE_CHILD, "true")
        }
        execute(request)
    }
    return client
}
