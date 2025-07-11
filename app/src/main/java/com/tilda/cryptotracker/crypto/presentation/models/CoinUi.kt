package com.tilda.cryptotracker.crypto.presentation.models

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.tilda.cryptotracker.core.domain.Coin
import com.tilda.cryptotracker.core.presentation.util.getDrawableIdForCoin
import java.util.Locale

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

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun Coin.toCoinUi() = CoinUi(
    id = id,
    symbol = symbol,
    name = name,
    rank = rank,
    priceUsd = priceUsd.toDisplayableNumber(),
    marketCapUsd = marketCapUsd.toDisplayableNumber(),
    changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
    iconRes = getDrawableIdForCoin(symbol)
)