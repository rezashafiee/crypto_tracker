package com.tilda.cryptotracker.crypto.data.mappers

import com.tilda.cryptotracker.crypto.data.network.dto.CoinDto
import com.tilda.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)