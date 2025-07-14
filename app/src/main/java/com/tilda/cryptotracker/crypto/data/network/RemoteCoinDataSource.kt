package com.tilda.cryptotracker.crypto.data.network

import com.tilda.cryptotracker.BuildConfig
import com.tilda.cryptotracker.core.data.network.constructUrl
import com.tilda.cryptotracker.core.data.network.safeCall
import com.tilda.cryptotracker.core.domain.util.NetworkError
import com.tilda.cryptotracker.core.domain.util.Result
import com.tilda.cryptotracker.core.domain.util.map
import com.tilda.cryptotracker.crypto.data.mappers.toCoin
import com.tilda.cryptotracker.crypto.data.mappers.toCoinPrice
import com.tilda.cryptotracker.crypto.data.network.dto.CoinHistoryDto
import com.tilda.cryptotracker.crypto.data.network.dto.CoinResponseDto
import com.tilda.cryptotracker.crypto.domain.Coin
import com.tilda.cryptotracker.crypto.domain.CoinDataSource
import com.tilda.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            ) {
                url.parameters.append(
                    "apiKey",
                    BuildConfig.API_KEY
                )
            }
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(urlString = constructUrl("/assets/$coinId/history")) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}