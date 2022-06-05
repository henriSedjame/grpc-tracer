package io.github.hsedjame.bet.service

import io.github.hsedjame.tracing.client.TracingContext
import kotlinx.coroutines.coroutineScope
import java.util.UUID

class BetRepository(private val context: TracingContext) {

    suspend fun addBet(bet: Bet): Result<Bet> = coroutineScope {

        context.span("BetRepository::addBet"){ span ->

            span.info("Adding new bet for the user ${bet.author}")

            if (existById(bet.id)) {
                span.error("A bet already exist with id ${bet.id}")
                return@span Result.failure(BetAlreadyExistError(bet.id))
            }

            Store.DB[bet.id] = bet

            span.info("Bet stored successfully")

            Result.success(bet)
        }

    }

    suspend fun getBets(name: String): Result<List<Bet>> = coroutineScope {
        context.span("BetRepository::getBets"){ span ->
            span.info("Get bets with author $name")
            Result.success(Store.DB.values.filter { it.author == name })
        }
    }

    private fun existById(id: UUID): Boolean = Store.DB[id] != null

}