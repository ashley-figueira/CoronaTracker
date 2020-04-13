package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity
import com.ashleyfigueira.data.remote.responses.StatsByCountryResponse
import com.ashleyfigueira.domain.common.Mapper
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import javax.inject.Inject

class CountryStatsEntityMapper @Inject constructor() : Mapper<StatsByCountryResponse, List<CountryStatsEntity>>() {
    override fun mapFrom(from: StatsByCountryResponse): List<CountryStatsEntity> {
        return from.countries_stat?.map {
            CountryStatsEntity(
                it?.country_name ?: "",
                it?.region ?: "",
                it?.active_cases?.toLongOrNull() ?: 0L,
                it?.cases?.toLongOrNull() ?: 0L,
                it?.deaths?.toLongOrNull() ?: 0L,
                it?.total_recovered?.toLongOrNull() ?: 0L,
                it?.new_cases?.toLongOrNull() ?: 0L,
                it?.new_deaths?.toLongOrNull() ?: 0L,
                it?.serious_critical?.toLongOrNull() ?: 0L,
                it?.total_cases_per_1m_population?.toLongOrNull() ?: 0L
            )
        } ?: emptyList()
    }

    fun mapToRoom(from: List<CountryStatsEntity>): List<CountryStatsRoomEntity> {
        return from.map {
            CountryStatsRoomEntity(
                it.countryName,
                it.region,
                it.activeCases,
                it.totalCases,
                it.totalDeaths,
                it.totalRecovered,
                it.newCases,
                it.newDeaths,
                it.seriouslyCritical,
                it.totalCasesPer1mPopulation
            )
        }
    }

    fun mapFromRoom(from: List<CountryStatsRoomEntity>): List<CountryStatsEntity> {
        return from.map {
            CountryStatsEntity(
                it.countryName,
                it.region,
                it.activeCases,
                it.totalCases,
                it.totalDeaths,
                it.totalRecovered,
                it.newCases,
                it.newDeaths,
                it.seriouslyCritical,
                it.totalCasesPer1mPopulation
            )
        }
    }
}