package com.ashleyfigueira.domain.entities

data class WorldStatsEntity(
    val newCases: Long,
    val newDeaths: Long,
    val totalCases: Long,
    val totalDeaths: Long,
    val totalRecovered: Long
)