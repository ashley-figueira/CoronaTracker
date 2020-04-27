package com.ashleyfigueira.coronatracker.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.ashleyfigueira.coronatracker.base.ScreenState
import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity
import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity
import com.ashleyfigueira.domain.common.CoronaError
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.common.TestDispatcherProvider
import com.ashleyfigueira.domain.common.toResult
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import com.ashleyfigueira.domain.usecases.GetAllCountriesStats
import com.ashleyfigueira.domain.usecases.GetWorldStatsUseCase
import com.jraska.livedata.TestLifecycle
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.joda.time.DateTime
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val lifecycleOwner: LifecycleOwner = mock()
    private val getWorldStatsUseCase: GetWorldStatsUseCase = mock()
    private val getAllCountriesStats: GetAllCountriesStats = mock()
    private val testDispatcher = TestCoroutineDispatcher()
    private val coroutineDispatcher = TestDispatcherProvider(testDispatcher)
    private lateinit var vm: MainViewModel

    //Fakes
    private val worldStats by lazy { WorldStatsEntity(1L, 1L, 1L, 1L, 1L) }
    private val countryStats by lazy { listOf(CountryStatsEntity("Spain", "", 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L)) }

    @Before
    fun setUp() {
        vm = MainViewModel(getWorldStatsUseCase, getAllCountriesStats, coroutineDispatcher)

    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(getWorldStatsUseCase, getAllCountriesStats)
    }

    @Test
    fun givenFetchWorldAndCountryStatsIsSuccessThenShowData() = testDispatcher.runBlockingTest {
        whenever(getWorldStatsUseCase()).thenReturn(worldStats.toResult())
        whenever(getAllCountriesStats()).thenReturn(countryStats.toResult())

        val screenState = vm.screenState.test()

        vm.onStart(lifecycleOwner)

        screenState
            .assertHasValue()
            .assertHistorySize(3)
            .valueHistory()
            .also {
                assertTrue { (it[0] as ScreenState.Loading).isLoading }
                assertFalse { (it[1] as ScreenState.Loading).isLoading }
                assertTrue { it[2] is ScreenState.Success }
            }

        verify(getAllCountriesStats).invoke()
        verify(getWorldStatsUseCase).invoke()
    }

    @Test
    fun givenUserHasNoInternetConnectionThenShowNoInternetConnectionScreen() = testDispatcher.runBlockingTest {
        whenever(getWorldStatsUseCase()).thenReturn(CoronaResult.Failure(CoronaError.Offline(Exception())))
        whenever(getAllCountriesStats()).thenReturn(CoronaResult.Failure(CoronaError.Offline(Exception())))

        val screenState = vm.screenState.test()

        vm.onStart(lifecycleOwner)

        screenState
            .assertHasValue()
            .assertHistorySize(3)
            .valueHistory()
            .also {
                assertTrue { (it[0] as ScreenState.Loading).isLoading }
                assertFalse { (it[1] as ScreenState.Loading).isLoading }
                assertTrue { it[2] is ScreenState.NoInternet }
            }

        verify(getAllCountriesStats).invoke()
        verify(getWorldStatsUseCase).invoke()
    }

    @Test
    fun givenRequestFailsForAnyreasonThenShowNoDataScreen() = testDispatcher.runBlockingTest {
        whenever(getWorldStatsUseCase()).thenReturn(CoronaResult.Failure(CoronaError.Unknown(Exception())))
        whenever(getAllCountriesStats()).thenReturn(CoronaResult.Failure(CoronaError.Unknown(Exception())))

        val screenState = vm.screenState.test()

        vm.onStart(lifecycleOwner)

        screenState
            .assertHasValue()
            .assertHistorySize(3)
            .valueHistory()
            .also {
                assertTrue { (it[0] as ScreenState.Loading).isLoading }
                assertFalse { (it[1] as ScreenState.Loading).isLoading }
                assertTrue { it[2] is ScreenState.Empty }
            }

        verify(getAllCountriesStats).invoke()
        verify(getWorldStatsUseCase).invoke()
    }
}