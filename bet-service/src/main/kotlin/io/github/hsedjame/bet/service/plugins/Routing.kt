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
import io.ktor.server.request.*
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
                    span.info("Receive add bet request")

                    val request = call.receive<AddBetRequest>()

                    val auth = call.request.headers[AUTHORIZATION] ?: run {
                        val message = "Authorization header missing"
                        span.error(message)
                        call.respond(HttpStatusCode.Unauthorized, message)
                        return@span
                    }

                    val parts = auth.split(":")

                    if (parts.size != 2) {
                        val message = "Malformed Authorization header"
                        span.error(message)
                        call.respond(HttpStatusCode.BadRequest, message)
                        return@span
                    }

                    val name = parts[0]

                    if (name != request.name) {
                        val message = "The Bet author must be the requester."
                        span.error(message)
                        call.respond(HttpStatusCode.Unauthorized, message)
                        return@span
                    }

                    val password = parts[1]

                    service.addBet(request, password).fold(
                        {
                            call.respond(it)
                        },
                        {
                            span.error("Failed to add new bet")
                            call.respond(fromError(it), it.message ?: "Unexpected error occurred")
                        }
                    )
                }
            }

            get("/{name}") {
                context.span("GET ::: /get/{name}") { span ->

                    span.info("Receive request getBets")

                    val name = call.parameters["name"] ?: run {
                        span.error("Path variable 'name' is missing")
                        call.respond(HttpStatusCode.BadRequest, "The path variable 'name' is required")
                        return@span
                    }

                    span.info("... for user $name")

                    service.getBets(name).fold(
                        {
                            call.respond(it)
                        },
                        {
                            call.respond(fromError(it), it.message ?: "Unexpected error occurred")
                        }
                    )
                    }
                }

        }
    }

}





