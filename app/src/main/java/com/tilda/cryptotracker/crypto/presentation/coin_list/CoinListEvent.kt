package com.tilda.cryptotracker.crypto.presentation.coin_list

import com.tilda.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}