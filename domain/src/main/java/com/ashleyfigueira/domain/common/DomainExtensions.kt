package com.ashleyfigueira.domain.common

import org.joda.time.DateTime

fun <T> T.toResult(): CoronaResult<T> {
    return CoronaResult.Success(this)
}

fun <T> CoronaError.toResult(): CoronaResult<T> {
    return CoronaResult.Failure(this)
}

fun <T> CoronaResult<T>.isSuccess(): Boolean {
    return this is CoronaResult.Success
}

fun <T> CoronaResult<T>.isFailure(): Boolean {
    return this is CoronaResult.Failure
}

fun <T> CoronaResult<T>.getData(): T {
    return (this as CoronaResult.Success).data
}

fun <T> CoronaResult<T>.getError(): CoronaError {
    return (this as CoronaResult.Failure).error
}

fun DateTime.isNotOutOfDateByOneDay(): Boolean = this.isBefore(DateTime.now().minusDays(1)).not()
