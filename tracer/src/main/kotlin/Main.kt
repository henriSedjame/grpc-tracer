
import io.github.hsedjame.grpc.tracing.Storage
import io.github.hsedjame.grpc.tracing.Trace
import io.github.hsedjame.grpc.tracing.services.TraceServiceImpl
import io.github.hsedjame.grpc.tracing.server.TracerServer
import io.github.hsedjame.grpc.tracing.services.DashboardServiceImpl
import reactor.core.publisher.Sinks

fun main() {
    val port = System.getenv()["--port"]?.toInt() ?: 7777
    val server = TracerServer(port){
        val storage = Storage()
        val sinks = Sinks.many().replay().all<Trace>()
        addService(TraceServiceImpl(storage, sinks))
        addService(DashboardServiceImpl(sinks))
    }
    server.start()
    server.blockUntilShutdown()
}