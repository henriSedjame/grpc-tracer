package io.github.hsedjame.user.service

data class User(
    val login: String,
    val password: String,
    val age: Int
)

data class UserRequest (
    val login: String,
    val password: String,
)