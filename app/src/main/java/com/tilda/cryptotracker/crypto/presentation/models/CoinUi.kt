package com.tilda.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes

data class CoinUi(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: DisplayableNumber,
    val marketCapUsd: DisplayableNumber,
    val rank: Int,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String,
)