package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.CountryStatsDao
import com.ashleyfigueira.data.local.WorldStatsDao
import com.ashleyfigueira.data.remote.ApiService
import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import javax.inject.Inject

class CoronaRepositoryImpl @Inject constructor(
    private val countryStatsDao: CountryStatsDao,
    private val worldStatsDao: WorldStatsDao,
    private val apiService: ApiService,
    private val countryStatsMapper: CountryStatsEntityMapper,
    private val worldStatsMapper: WorldStatsEntityMapper
) : CoronaRepository {

    //TODO: check if we have data in DB and if so return that.
    override suspend fun getAllCountryStats(): CoronaResult<List<CountryStatsEntity>> = safeCall {
        val response = apiService.getCountryWiseCases()
        val mappedResponse = countryStatsMapper.mapFrom(response)
        countryStatsDao.insertCountryStats(countryStatsMapper.mapToRoom(mappedResponse))
        mappedResponse
    }

    override suspend fun getWorldStats(): CoronaResult<WorldStatsEntity> = safeCall {
        val response = apiService.getWorldStats()
        val mappedResponse = worldStatsMapper.mapFrom(response)
        worldStatsDao.insertWorldStats(worldStatsMapper.mapToRoom(mappedResponse))
        mappedResponse
    }
}