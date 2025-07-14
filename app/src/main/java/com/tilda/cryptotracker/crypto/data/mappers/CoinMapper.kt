package com.tilda.cryptotracker.crypto.data.mappers

import com.tilda.cryptotracker.crypto.data.network.dto.CoinDto
import com.tilda.cryptotracker.crypto.data.network.dto.CoinPriceDto
import com.tilda.cryptotracker.crypto.domain.Coin
import com.tilda.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin() = Coin(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr
)

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}