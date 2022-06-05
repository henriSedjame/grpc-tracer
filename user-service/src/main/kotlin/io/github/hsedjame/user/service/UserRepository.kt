package io.github.hsedjame.user.service

import io.github.hsedjame.tracing.client.TracingContext
import kotlinx.coroutines.coroutineScope

class UserRepository(private val tracingContext: TracingContext) {

    suspend fun findByLogin(request: UserRequest) : Result<User> = coroutineScope {

        tracingContext.span("UserRepository::FindByLogin") { span ->

            request.login.let { login ->

                span.info("Start searching a user with login : $login")

                Store.DB[login]?.let { user ->

                    span.info("User found { login : ${user.login}, age: ${user.age}}")

                    span.info("Check user credentials")

                    if (user.password == request.password) {

                        span.info("Credentials match !!!")

                        Result.success(user)
                    } else {

                        span.error("Credentials do not match")

                        Result.failure(BadCredentialsError())
                    }


                } ?: run {

                    span.error("User not found with login $login")

                    Result.failure(UserNotFoundError(login))
                }
            }

        }
    }

}