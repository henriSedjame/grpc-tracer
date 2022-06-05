package io.github.hsedjame.bet.service

import io.github.hsedjame.tracing.client.TracingContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BetService(private val context: TracingContext, private val httpClient: HttpClient, private val repository: BetRepository) {

    suspend fun addBet(request: AddBetRequest, password: String): Result<Bet> = coroutineScope {

        data class CheckRequest(val login: String, val password: String)

        context.span("BetService::addBet"){ span ->
            val name = request.name

            span.info("Start adding new bet logic")

            span.info("Request a user check to UserService")

            val checkResult = httpClient.post("http://localhost:9001/users/check") {
                contentType(ContentType.Application.Json)
                setBody(CheckRequest(name, password))
            }.body<Boolean>()

            span.info("Response received from UserService : $checkResult")

            if (!checkResult) {
                return@span Result.failure(BetForbidden(name))
            }

            repository.addBet(Bet(UUID.randomUUID(), name, request.amount, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)))

        }
    }

    suspend fun getBets(name: String) = coroutineScope {
        context.span("BetService::getBets") { span ->
            span.info("Get user bets")
            repository.getBets(name)
        }
    }
}