package com.tilda.cryptotracker.crypto.data.network

import com.tilda.cryptotracker.core.data.network.constructUrl
import com.tilda.cryptotracker.core.data.network.safeCall
import com.tilda.cryptotracker.core.domain.util.NetworkError
import com.tilda.cryptotracker.core.domain.util.Result
import com.tilda.cryptotracker.core.domain.util.map
import com.tilda.cryptotracker.crypto.data.mappers.toCoin
import com.tilda.cryptotracker.crypto.data.network.dto.CoinResponseDto
import com.tilda.cryptotracker.crypto.domain.Coin
import com.tilda.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}