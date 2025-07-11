package com.tilda.cryptotracker.core.data.network

import com.tilda.cryptotracker.core.domain.util.NetworkError
import com.tilda.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        // if the coroutine would be canceled then this could catch the CancellationException
        // therefor the following line is added
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}