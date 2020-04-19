package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.CountryStatsDao
import com.ashleyfigueira.data.local.WorldStatsDao
import com.ashleyfigueira.data.remote.ApiService
import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.common.isNotOutOfDateByOneDay
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import org.joda.time.DateTime
import javax.inject.Inject

class CoronaRepositoryImpl @Inject constructor(
    private val countryStatsDao: CountryStatsDao,
    private val worldStatsDao: WorldStatsDao,
    private val apiService: ApiService,
    private val countryStatsMapper: CountryStatsEntityMapper,
    private val worldStatsMapper: WorldStatsEntityMapper
) : CoronaRepository {

    override suspend fun getAllCountryStats(): CoronaResult<List<CountryStatsEntity>> = safeCall {
        val dbCountryStats = countryStatsDao.getCountryStats()

        //We have data from Db and data is valid (return that)
        if (dbCountryStats.isNotEmpty() && DateTime(dbCountryStats.first().lastUpdatedAt).isNotOutOfDateByOneDay()) {
            countryStatsMapper.mapFromRoom(dbCountryStats)
        } else {
            //Other wise go to network and save it in DB.
            val response = apiService.getCountryWiseCases()
            val mappedResponse = countryStatsMapper.mapFrom(response)
            countryStatsDao.insertCountryStats(countryStatsMapper.mapToRoom(mappedResponse))
            mappedResponse
        }

    }

    override suspend fun getWorldStats(): CoronaResult<WorldStatsEntity> = safeCall {
        val dbWorldStats = worldStatsDao.getWorldStats()

        //We have data from Db and data is valid (return that)
        if (dbWorldStats != null && DateTime(dbWorldStats.lastUpdatedAt).isNotOutOfDateByOneDay()) {
            worldStatsMapper.mapFromRoom(dbWorldStats)
        } else {
            //Other wise go to network and save it in DB.
            val response = apiService.getWorldStats()
            val mappedResponse = worldStatsMapper.mapFrom(response)
            worldStatsDao.insertWorldStats(worldStatsMapper.mapToRoom(mappedResponse))
            mappedResponse
        }
    }
}