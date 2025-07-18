package com.tilda.cryptotracker.core.presentation.util

import android.content.Context
import com.tilda.cryptotracker.R
import com.tilda.cryptotracker.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.NO_INTERNET_CONNECTION -> R.string.no_internet_connection
        NetworkError.SERVER_ERROR -> R.string.unknown_error
        NetworkError.SERIALIZATION_ERROR -> R.string.unknown_error
        NetworkError.UNKNOWN_ERROR -> R.string.unknown_error
    }
    return context.getString(resId)
}