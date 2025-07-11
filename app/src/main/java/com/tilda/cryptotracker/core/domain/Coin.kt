package com.tilda.cryptotracker.core.domain

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val rank: Int,
    val changePercent24Hr: Double,
)

