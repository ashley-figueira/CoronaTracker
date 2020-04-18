package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity
import com.ashleyfigueira.data.remote.responses.StatsByCountryResponse
import com.ashleyfigueira.domain.common.Mapper
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import java.lang.Exception
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class CountryStatsEntityMapper @Inject constructor() : Mapper<StatsByCountryResponse, List<CountryStatsEntity>>() {
    override fun mapFrom(from: StatsByCountryResponse): List<CountryStatsEntity> {
        return from.countries_stat?.map {
            CountryStatsEntity(
                it?.country_name ?: "",
                it?.region ?: "",
                formatToLong(it?.active_cases),
                formatToLong(it?.cases),
                formatToLong(it?.deaths),
                formatToLong(it?.total_recovered),
                formatToLong(it?.new_cases),
                formatToLong(it?.new_deaths),
                formatToLong(it?.serious_critical),
                formatToLong(it?.total_cases_per_1m_population)
            )
        } ?: emptyList()
    }

    private fun formatToLong(from: String?): Long {
        return from?.let {
            try {
                NumberFormat.getNumberInstance(Locale.US).parse(it).toLong()
            } catch (e: Exception) {
                0L
            }
        } ?: 0L
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