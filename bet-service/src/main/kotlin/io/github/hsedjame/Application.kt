package io.github.hsedjame

import io.github.hsedjame.bet.service.BetRepository
import io.github.hsedjame.bet.service.BetService
import io.github.hsedjame.bet.service.plugins.configureRouting
import io.github.hsedjame.tracing.client.TracingContext
import io.github.hsedjame.tracing.client.httpClient
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val context = TracingContext("localhost", 7777, "BET-SERVICE")
    val httpClient = httpClient(context)
    val repository = BetRepository(context)
    val service = BetService(context, httpClient, repository)

    configureRouting(context, service)
}
