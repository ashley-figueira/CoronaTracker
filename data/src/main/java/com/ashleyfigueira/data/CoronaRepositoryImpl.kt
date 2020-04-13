package com.ashleyfigueira.data

import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import javax.inject.Inject

class CoronaRepositoryImpl @Inject constructor() : CoronaRepository {

    override suspend fun getAllCountryStats(): CoronaResult<List<CountryStatsEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getWorldStats(): CoronaResult<WorldStatsEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}