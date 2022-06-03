package io.github.hsedjame.grpc.tracing

import io.github.hsedjame.grpc.tracing.models.Log
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

data class Lifetime(val start: LocalDateTime = LocalDateTime.now(), val end: LocalDateTime?= null) {

    companion object{
        fun end(lifetime: Lifetime): Lifetime = lifetime.copy(end = LocalDateTime.now())
    }
    fun duration(): Long =
        end?.let{
            Duration.between(start, end).toMillis()
        } ?: 0L

}

data class Span(
    val name: String,
    val lifetime: Lifetime = Lifetime(),
    val logs: List<Log> = listOf(),
    val closed: Boolean = false
)

data class Trace(
    val correlationId: UUID,
    val name: String,
    val lifetime: Lifetime = Lifetime(),
    val spans: List<Span> = listOf(),
    val closed: Boolean = false
)