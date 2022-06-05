package io.github.hsedjame.user.service

object Store {
    val DB = mapOf<String, User>(
        "joe" to User("Joe", "joe1234", 24),
        "john" to User("John", "john1234", 17)
    )
}