package io.github.hsedjame.tracing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import java.util.*

const val CORRELATION_ID = "correlation-id"

fun createTracingPlugin(client: TracingClient) : ApplicationPlugin<TracingClient> =
    createApplicationPlugin("tracingPlugin", { client }){
        onCall {
            val id = this.pluginConfig.createTrace(it.request.path())
            it.attributes.put(AttributeKey(CORRELATION_ID), id)
        }

        onCallRespond { call, _ ->

            val task = object : TimerTask() {
                override fun run() {
                    val id = call.attributes[AttributeKey<UUID>(CORRELATION_ID)]
                    runBlocking {
                        pluginConfig.closeTrace(id)
                    }
                }
            }

            Timer().schedule(task, 10)
        }
    }
