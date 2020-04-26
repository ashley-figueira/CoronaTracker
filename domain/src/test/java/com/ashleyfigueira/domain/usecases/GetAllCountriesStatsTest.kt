package com.ashleyfigueira.domain.usecases

import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.common.TestDispatcherProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class GetAllCountriesStatsTest {

    private val coronaRepository: CoronaRepository = mock()
    val testDispatcher = TestCoroutineDispatcher()
    private val coroutineDispatcher = TestDispatcherProvider(testDispatcher)
    private lateinit var getAllCountriesStats: GetAllCountriesStats

    @Before
    fun setUp() {
        getAllCountriesStats = GetAllCountriesStats(coronaRepository, coroutineDispatcher)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(coronaRepository)
    }

    @Test
    fun invoke() = testDispatcher.runBlockingTest {
        whenever(coronaRepository.getAllCountryStats())
            .thenReturn(CoronaResult.Success(emptyList()))

        val result = getAllCountriesStats()
        assertTrue { result is CoronaResult.Success }

        verify(coronaRepository).getAllCountryStats()
    }
}