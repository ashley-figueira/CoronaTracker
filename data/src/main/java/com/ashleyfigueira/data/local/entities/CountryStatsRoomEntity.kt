package com.ashleyfigueira.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "country_stats")
data class CountryStatsRoomEntity(
    @PrimaryKey val countryName: String,
    val region: String,
    val activeCases: Long,
    val totalCases: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val newCases: Long,
    val newDeaths: Long,
    val seriouslyCritical: Long,
    val totalCasesPer1mPopulation: Long
)