package com.ashleyfigueira.domain.common

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun computation(): CoroutineDispatcher
}