package io.github.hsedjame

import io.ktor.server.application.*
import io.github.hsedjame.plugins.*
import io.github.hsedjame.tracing.TracingClient
import io.github.hsedjame.tracing.TracingContext
import io.grpc.ManagedChannelBuilder

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 7777)
        .usePlaintext()
        .build();

    val tracingClient = TracingClient(channel)

    val context = TracingContext(tracingClient)

    configureRouting(context, HelloService(context))
}
