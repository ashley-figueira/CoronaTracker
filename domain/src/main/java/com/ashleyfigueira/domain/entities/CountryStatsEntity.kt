package com.ashleyfigueira.domain.entities

data class CountryStatsEntity(
    val countryName: String,
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