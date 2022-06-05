package io.github.hsedjame.grpc.tracing

import kotlinx.coroutines.coroutineScope
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class Storage {
    private val store : ConcurrentHashMap<UUID, Trace> = ConcurrentHashMap()

     fun save(trace: Trace) : Result<Trace> =
        handle {
            if (existById(trace.correlationId)) throw AlreadyExistError()
            store[trace.correlationId] = trace
            trace
        }


     fun update(trace: Trace) : Result<Trace>  =
        handle {
            if (!existById(trace.correlationId)) throw NotFoundError()
            store[trace.correlationId] = trace
            trace
        }


     fun findAll() : List<Trace> = store.values.toList()


     fun findById(id: UUID) : Result<Trace> =
        handle {
            store[id] ?: throw NotFoundError()
        }

    private fun existById(id: UUID): Boolean =  store.containsKey(id)

}
