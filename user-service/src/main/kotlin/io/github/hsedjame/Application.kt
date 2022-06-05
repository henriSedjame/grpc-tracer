package io.github.hsedjame

import io.github.hsedjame.tracing.client.TracingContext
import io.github.hsedjame.user.service.UserRepository
import io.github.hsedjame.user.service.UserService
import io.github.hsedjame.user.service.plugins.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val context = TracingContext("localhost", 7777, "USER-SERVICE")
    val repository = UserRepository(context)
    val service = UserService(context, repository)

    configureRouting(context, service)
}
