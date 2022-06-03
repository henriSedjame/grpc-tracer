
import io.github.hsedjame.grpc.tracing.Storage
import io.github.hsedjame.grpc.tracing.services.TraceServiceImpl
import io.github.hsedjame.grpc.tracing.server.TracerServer
import io.github.hsedjame.grpc.tracing.services.DashboardServiceImpl

fun main() {
    val port = System.getenv()["--port"]?.toInt() ?: 7777
    val server = TracerServer(port){
        val storage = Storage()
        addService(TraceServiceImpl(storage))
        addService(DashboardServiceImpl(storage))
    }
    server.start()
    server.blockUntilShutdown()
}