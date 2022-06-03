package io.github.hsedjame

import io.github.hsedjame.tracing.TracingContext

class HelloService(val context: TracingContext) {

    suspend fun sayHello(): String = context.span("HelloService.sayHello") {
        it.info("Start helloService.sayHallo method")
        "Hello World".also { _ ->
            it.info("End helloService.sayHallo method")
        }
    }

    suspend fun sayGoodbye() : String = context.span("HelloService.sayGoodbye") {
        it.info("Start helloService.sayGoodbye method")
        "Good bye".also { _ ->
            it.info("End helloService.sayGoodBye method")
        }
    }
}