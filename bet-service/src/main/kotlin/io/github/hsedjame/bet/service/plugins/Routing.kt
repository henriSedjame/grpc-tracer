package io.github.hsedjame.bet.service.plugins


import io.github.hsedjame.bet.service.AddBetRequest
import io.github.hsedjame.bet.service.BetService
import io.github.hsedjame.bet.service.fromError
import io.github.hsedjame.tracing.client.TracingContext
import io.github.hsedjame.tracing.client.createTracingPlugin
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val AUTHORIZATION = "Authorization"

fun Application.configureRouting(context: TracingContext, service: BetService) {

    install(createTracingPlugin(context))

    install(ContentNegotiation){
        gson {}
    }

    routing {

        route("/bets") {

            post("") {

                context.span("POST ::: /bets") { span ->

                    val request = span.logBody<AddBetRequest>(call)

                    val auth = call.request.headers[AUTHORIZATION] ?: run {
                        val message = "Authorization header missing"
                        span.error("Error: $message")
                        call.respond(HttpStatusCode.Unauthorized, message)
                        return@span
                    }

                    val parts = auth.split(":")

                    if (parts.size != 2) {
                        val message = "Malformed Authorization header"
                        span.error("Error: $message")
                        call.respond(HttpStatusCode.BadRequest, message)
                        return@span
                    }

                    val name = parts[0]

                    if (name != request.name) {
                        val message = "The Bet author must be the requester."
                        span.error("Error: $message")
                        call.respond(HttpStatusCode.Unauthorized, message)
                        return@span
                    }

                    val password = parts[1]

                    service.addBet(request, password).fold(
                        {
                            span.info("Bet registered : $it")
                            call.respond(it)
                        },
                        {
                            span.error("Error: ${it.message}")
                            call.respond(fromError(it), it.message ?: "Unexpected error occurred")
                        }
                    )
                }
            }

            get("/{name}") {
                context.span("GET ::: /get/{name}") { span ->

                    val name = call.parameters["name"] ?: run {
                        span.error("Error: Path variable 'name' is missing")
                        call.respond(HttpStatusCode.BadRequest, "The path variable 'name' is required")
                        return@span
                    }

                    span.info("Request with path variable name=$name")

                    service.getBets(name).fold(
                        {
                            span.info("$name's lits of bets found: $it")
                            call.respond(it)
                        },
                        {
                            span.error("Error: ${it.message}")
                            call.respond(fromError(it), it.message ?: "Unexpected error occurred")
                        }
                    )
                    }
                }

        }
    }

}





