package io.github.hsedjame.plugins

import io.github.hsedjame.HelloService
import io.github.hsedjame.tracing.TracingContext
import io.github.hsedjame.tracing.createTracingPlugin
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(context: TracingContext, service: HelloService) {

    install(createTracingPlugin(context.client))

    // Starting point for a Ktor app:
    routing {

        get("/hello") {
            context.span("Routing.GET.hello") {

                it.info("Start call /hello")

                val hello = service.sayHello()

                it.info("End call /hello")

                call.respondText(hello)
            }
        }

        get("/bye") {
            context.span("Routing.GET.bye") {

                it.info("Start call /bye")

                val hello = service.sayGoodbye()

                it.info("End call /bye")

                call.respondText(hello)
            }
        }
    }
    routing {
    }
}





