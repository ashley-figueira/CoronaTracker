package com.ashleyfigueira.domain.usecases

import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.common.TestDispatcherProvider
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class GetWorldStatsUseCaseTest {

    private val coronaRepository: CoronaRepository = mock()
    val testDispatcher = TestCoroutineDispatcher()
    private val coroutineDispatcher = TestDispatcherProvider(testDispatcher)
    private lateinit var getWorldStatsUseCase: GetWorldStatsUseCase

    @Before
    fun setUp() {
        getWorldStatsUseCase = GetWorldStatsUseCase(coronaRepository, coroutineDispatcher)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(coronaRepository)
    }

    @Test
    fun invoke() = testDispatcher.runBlockingTest {
        whenever(coronaRepository.getWorldStats())
            .thenReturn(CoronaResult.Success(WorldStatsEntity(1L,1L,1L,1L,1L)))

        val result = getWorldStatsUseCase()
        assertTrue { result is CoronaResult.Success }

        verify(coronaRepository).getWorldStats()
    }
}