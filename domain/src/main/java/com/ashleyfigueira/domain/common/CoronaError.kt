package com.ashleyfigueira.domain.common

sealed class CoronaError(val throwable: Throwable) {
    class Offline(throwable: Throwable) : CoronaError(throwable)
    class Timeout(throwable: Throwable) : CoronaError(throwable)
    class Unknown(throwable: Throwable) : CoronaError(throwable)
    class NothingInDatabase(throwable: Throwable) : CoronaError(throwable)
}