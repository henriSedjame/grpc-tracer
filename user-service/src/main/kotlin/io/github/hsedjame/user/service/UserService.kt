package io.github.hsedjame.user.service

import io.github.hsedjame.tracing.client.TracingContext
import kotlinx.coroutines.coroutineScope

class UserService(private val tracingContext: TracingContext, private val repository: UserRepository) {

    suspend fun isPermit(request: UserRequest): Result<Boolean> = coroutineScope {
        tracingContext.span("UserService::isPermit") { span ->
            span.info("Start checking if user exist and if user is major")

            repository.findByLogin(request).fold(
                {
                   Result.success((it.age >= 18).also {major ->
                       if (major) span.info("User is major") else span.info("User is minor")
                   })
                }, {
                    Result.failure(it)
                }
            )
        }
    }


}