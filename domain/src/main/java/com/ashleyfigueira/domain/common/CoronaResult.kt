package com.ashleyfigueira.domain.common

sealed class CoronaResult<out T> {
    data class Success<out T>(val data: T) : CoronaResult<T>()
    data class Failure<T>(val error: CoronaError) : CoronaResult<T>()
}