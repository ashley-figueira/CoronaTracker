package com.ashleyfigueira.data

import com.ashleyfigueira.domain.common.CoronaError
import com.ashleyfigueira.domain.common.CoronaResult
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeCall(call: suspend () -> T): CoronaResult<T> {
    return try {
        val result = call()
        CoronaResult.Success(result)
    } catch (throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> CoronaResult.Failure(CoronaError.Offline(throwable))
            is SocketTimeoutException -> CoronaResult.Failure(CoronaError.Timeout(throwable))
            else -> CoronaResult.Failure(CoronaError.Unknown(throwable))
        }
    }
}