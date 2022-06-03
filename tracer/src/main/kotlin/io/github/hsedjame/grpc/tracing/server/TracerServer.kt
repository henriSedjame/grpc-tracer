package io.github.hsedjame.grpc.tracing.server

import io.grpc.Server
import io.grpc.ServerBuilder

class TracerServer(
    private val port: Int,
    addServices: ServerBuilder<*>.() -> Unit,
) {

    companion object {
        const val banner = """
        _________________ ________ _________       ________________ _______ ___________________________ 
        __  ____/___  __ \___  __ \__  ____/       ___  __/___  __ \___    |__  ____/___  ____/___  __ \
        _  / __  __  /_/ /__  /_/ /_  /            __  /   __  /_/ /__  /| |_  /     __  __/   __  /_/ /
        / /_/ /  _  _, _/ _  ____/ / /___          _  /    _  _, _/ _  ___ |/ /___   _  /___   _  _, _/ 
        \____/   /_/ |_|  /_/      \____/          /_/     /_/ |_|  /_/  |_|\____/   /_____/   /_/ |_|                                                                                                  
        """

    }

    private val server: Server = ServerBuilder.forPort(port).apply(addServices).build()

    fun start() {
        println()
        println(banner.trimIndent())
        server.start()
        println()
        println("__________________________________________________________________________")
        println("  LIST OF GRPC SERVICES EXPOSED                  ")
        println("--------------------------------------------------------------------------")
        println()
        server.services.forEach{
            println("  *  ${it.serviceDescriptor.name}")
            println("        _________________________")
            println("            LIST OF PROTOCOLS    ")
            println("        __________________________")
            println()
            it.methods.forEach { m ->
                println("        *** ${m.methodDescriptor.bareMethodName}")
            }
            println("        __________________________")
            println()
        }
        println()
        println("__________________________________________________________________________")
        println()
        println(" . . . . . . . . . . . . . . . . . Grpc server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@TracerServer.stop()
                println("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown();
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

}