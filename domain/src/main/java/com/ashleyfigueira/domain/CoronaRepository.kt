package com.ashleyfigueira.domain

import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity

interface CoronaRepository {
    suspend fun getAllCountryStats(): CoronaResult<List<CountryStatsEntity>>
    suspend fun getWorldStats(): CoronaResult<WorldStatsEntity>
}