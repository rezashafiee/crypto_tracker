package com.tilda.cryptotracker.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val rank: Int,
    val changePercent24Hr: Double,
)
