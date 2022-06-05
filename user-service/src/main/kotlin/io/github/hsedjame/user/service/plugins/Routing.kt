package io.github.hsedjame.user.service.plugins

import io.github.hsedjame.tracing.client.TracingContext
import io.github.hsedjame.tracing.client.createTracingPlugin
import io.github.hsedjame.user.service.UserRequest
import io.github.hsedjame.user.service.UserService
import io.github.hsedjame.user.service.statusFrom
import io.ktor.serialization.gson.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*


fun Application.configureRouting(context: TracingContext, service: UserService) {

    install(createTracingPlugin(context))

    install(ContentNegotiation){
        gson {}
    }

    routing {
        route("/users") {
            post("/check") {
                context.span("POST ::: /users/check"){ span ->

                    span.info("Receive check request")

                    val request = call.receive<UserRequest>()

                    val result = service.isPermit(request)

                    result.fold(
                        {
                            call.respond(it)
                        },
                        {
                            call.respond(statusFrom(it), it.message ?: "Unexpected error occurred")
                        }
                    )

                }
            }
        }
    }

}
