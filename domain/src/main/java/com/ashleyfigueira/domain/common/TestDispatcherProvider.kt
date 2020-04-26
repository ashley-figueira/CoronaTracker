package com.ashleyfigueira.domain.common

import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcherProvider(private val dispatcher: CoroutineDispatcher) : DispatcherProvider {
    override fun ui(): CoroutineDispatcher = dispatcher
    override fun computation(): CoroutineDispatcher = dispatcher
    override fun io(): CoroutineDispatcher = dispatcher
}