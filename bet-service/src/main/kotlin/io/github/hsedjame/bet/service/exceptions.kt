package io.github.hsedjame.bet.service

import io.ktor.http.*
import java.util.UUID

sealed class BetException(override val message: String): Exception(message)

class BetAlreadyExistError(private val id: UUID): BetException("A bet already exist with id $id")

class BetForbidden(private val name: String) : BetException("Bet not permitted for user $name")

fun fromError(t: Throwable): HttpStatusCode =
    when(t) {
        is BetAlreadyExistError -> HttpStatusCode.BadRequest
        is BetForbidden -> HttpStatusCode.Forbidden
        else -> HttpStatusCode.InternalServerError
    }
