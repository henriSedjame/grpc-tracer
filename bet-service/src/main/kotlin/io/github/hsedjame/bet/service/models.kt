package io.github.hsedjame.bet.service

import java.util.UUID

data class Bet(
    val id: UUID,
    val author: String,
    val amount: Float,
    val date: String
)


data class AddBetRequest(
    val name: String,
    val amount: Float
)