package com.tilda.cryptotracker.crypto.domain

import com.tilda.cryptotracker.core.domain.util.NetworkError
import com.tilda.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}