package io.github.hsedjame.grpc.tracing

fun <T> handle(f: () -> T) : Result<T> =
    try {
        val t = f()
        Result.success(t)
    } catch (e: Exception){
        Result.failure(e)
    }

suspend fun <T, R> handleResult(f: suspend () -> Result<T>, handler: suspend (T) -> R) = f().fold(
    {
        handler(it)
    } ,
    {
       throw from(it)
    }
)

fun <T> requireNotNull(f: () -> T?): T = f() ?: throw UnexpectedError()

fun <T> handleFaillible(message: String, f: () -> T) =
    try {
        f()
    }catch (e: Exception){
        throw from(e, message)
    }