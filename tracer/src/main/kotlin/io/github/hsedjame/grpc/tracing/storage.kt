package io.github.hsedjame.grpc.tracing

import kotlinx.coroutines.coroutineScope
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class Storage {
    private val store : ConcurrentHashMap<UUID, Trace> = ConcurrentHashMap()

    suspend fun save(trace: Trace) : Result<Trace> = coroutineScope {
        handle {
            if (existById(trace.correlationId)) throw AlreadyExistError()
            store[trace.correlationId] = trace
            trace
        }
    }

    suspend fun update(trace: Trace) : Result<Trace>  = coroutineScope {
        handle {
            if (!existById(trace.correlationId)) throw NotFoundError()
            store[trace.correlationId] = trace
            trace
        }
    }

    suspend fun findAll() : List<Trace> = coroutineScope {
        store.values.toList()
    }

    suspend fun findById(id: UUID) : Result<Trace> = coroutineScope {
        handle {
            store[id] ?: throw NotFoundError()
        }
    }

    private fun existById(id: UUID): Boolean =  store.containsKey(id)

}
