package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.CountryStatsDao
import com.ashleyfigueira.data.local.WorldStatsDao
import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity
import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity
import com.ashleyfigueira.data.remote.ApiService
import com.ashleyfigueira.data.remote.responses.StatsByCountryResponse
import com.ashleyfigueira.data.remote.responses.WorldStatsResponse
import com.ashleyfigueira.data.utils.LocalJsonFileParsingUtil
import com.ashleyfigueira.domain.common.CoronaResult
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.joda.time.DateTime
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class CoronaRepositoryImplTest {

    private val countryStatsDao: CountryStatsDao = mock()
    private val worldStatsDao: WorldStatsDao = mock()
    private val apiService: ApiService = mock()
    private val countryStatsMapper = CountryStatsEntityMapper()
    private val worldStatsMapper = WorldStatsEntityMapper()
    private lateinit var coronaRepositoryImpl: CoronaRepositoryImpl

    //Fakes
    private val worldStatsResponse by lazy { LocalJsonFileParsingUtil.localJsonFileToObject(WorldStatsResponse::class.java, "world_stats_response.json") }
    private val countryStatsResponse by lazy { LocalJsonFileParsingUtil.localJsonFileToObject(StatsByCountryResponse::class.java, "country_stats_response.json") }
    private val worldStatsDaoResult by lazy { WorldStatsRoomEntity(1L, 1L, 1L, 1L, 1L, DateTime.now().millis) }
    private val countryStatsDaoResult by lazy { CountryStatsRoomEntity("Spain", "", 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, DateTime.now().millis) }

    @Before
    fun setUp() {
        coronaRepositoryImpl = CoronaRepositoryImpl(countryStatsDao, worldStatsDao, apiService, countryStatsMapper, worldStatsMapper)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(countryStatsDao, worldStatsDao, apiService)
    }

    @Test
    fun givenThatWorldStatsExistsInDbThenJustReturnFromDb() = runBlockingTest {
        whenever(worldStatsDao.getWorldStats()).thenReturn(worldStatsDaoResult)

        val result = coronaRepositoryImpl.getWorldStats()
        assertTrue { result is CoronaResult.Success }

        verify(worldStatsDao).getWorldStats()
        verify(worldStatsDao, never()).insertWorldStats(any())
        verify(apiService, never()).getWorldStats()
    }

    @Test
    fun givenThatWorldStatsDbHasExpiredThanGoToNetworkAndSaveToDb() = runBlockingTest {
        whenever(worldStatsDao.getWorldStats())
            .thenReturn(worldStatsDaoResult.copy(lastUpdatedAt = DateTime.now().minusDays(2).millis))
        whenever(apiService.getWorldStats()).thenReturn(worldStatsResponse)

        val result = coronaRepositoryImpl.getWorldStats()
        assertTrue { result is CoronaResult.Success }

        verify(apiService).getWorldStats()
        verify(worldStatsDao).insertWorldStats(any())
        verify(worldStatsDao).getWorldStats()
    }

    @Test
    fun givenThatWorldStatsDbIsEmptyThanGoToNetworkAndSaveToDb() = runBlockingTest {
        whenever(worldStatsDao.getWorldStats()).thenReturn(null)
        whenever(apiService.getWorldStats()).thenReturn(worldStatsResponse)

        val result = coronaRepositoryImpl.getWorldStats()
        assertTrue { result is CoronaResult.Success }

        verify(apiService).getWorldStats()
        verify(worldStatsDao).insertWorldStats(any())
        verify(worldStatsDao).getWorldStats()
    }

    @Test
    fun givenThatCountryStatsExistsInDbThenJustReturnFromDb() = runBlockingTest {
        whenever(countryStatsDao.getCountryStats()).thenReturn(listOf(countryStatsDaoResult))

        val result = coronaRepositoryImpl.getAllCountryStats()
        assertTrue { result is CoronaResult.Success }

        verify(apiService, never()).getCountryWiseCases()
        verify(countryStatsDao, never()).insertCountryStats(any())
        verify(countryStatsDao).getCountryStats()
    }

    @Test
    fun givenThaCountryStatsDbHasExpiredThanGoToNetworkAndSaveToDb() = runBlockingTest {
        whenever(apiService.getCountryWiseCases()).thenReturn(countryStatsResponse)
        whenever(countryStatsDao.getCountryStats()).thenReturn(listOf(countryStatsDaoResult.copy(lastUpdatedAt = DateTime.now().minusDays(2).millis)))

        val result = coronaRepositoryImpl.getAllCountryStats()
        assertTrue { result is CoronaResult.Success }

        verify(apiService).getCountryWiseCases()
        verify(countryStatsDao).insertCountryStats(any())
        verify(countryStatsDao).getCountryStats()
    }

    @Test
    fun givenThatCountryStatsDbIsEmptyThanGoToNetworkAndSaveToDb() = runBlockingTest {
        whenever(apiService.getCountryWiseCases()).thenReturn(countryStatsResponse)
        whenever(countryStatsDao.getCountryStats()).thenReturn(emptyList())

        val result = coronaRepositoryImpl.getAllCountryStats()
        assertTrue { result is CoronaResult.Success }

        verify(apiService).getCountryWiseCases()
        verify(countryStatsDao).insertCountryStats(any())
        verify(countryStatsDao).getCountryStats()
    }
}