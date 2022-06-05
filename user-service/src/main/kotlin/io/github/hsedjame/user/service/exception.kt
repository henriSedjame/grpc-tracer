package io.github.hsedjame.user.service

import io.ktor.http.*

sealed class UserException(override val message: String): Exception(message)

class BadCredentialsError : UserException("Bad credentials")

class UserNotFoundError(private val login: String): UserException("User not found with login $login")

fun statusFrom(t: Throwable) : HttpStatusCode =
    when (t) {
        is BadCredentialsError -> HttpStatusCode.Unauthorized
        is UserNotFoundError -> HttpStatusCode.NotFound
        else -> HttpStatusCode.InternalServerError
    }