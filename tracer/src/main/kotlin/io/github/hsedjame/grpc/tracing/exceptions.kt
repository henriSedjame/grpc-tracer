package io.github.hsedjame.grpc.tracing

import io.grpc.Status
import io.grpc.StatusException

class UnexpectedError: Exception("Unexpected error occurred")

sealed class StorageException(message: String): Exception(message)
class AlreadyExistError : StorageException("Trace already exist")
class NotFoundError : StorageException("Trace not found")

fun from(e: Throwable, description: String?=null): StatusException {
    val status = when (e) {
        is AlreadyExistError -> Status.ALREADY_EXISTS
        is NotFoundError -> Status.NOT_FOUND
        is UnexpectedError -> Status.INTERNAL
        else -> Status.INTERNAL
    }
    return StatusException(status.withDescription(description ?: e.message))
}