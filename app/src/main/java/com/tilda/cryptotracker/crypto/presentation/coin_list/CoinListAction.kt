package com.tilda.cryptotracker.crypto.presentation.coin_list

import com.tilda.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}