package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity
import com.ashleyfigueira.data.remote.responses.WorldStatsResponse
import com.ashleyfigueira.domain.common.Mapper
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import javax.inject.Inject

class WorldStatsEntityMapper @Inject constructor() : Mapper<WorldStatsResponse, WorldStatsEntity>() {

    override fun mapFrom(from: WorldStatsResponse): WorldStatsEntity {
        return WorldStatsEntity(
            from.new_cases?.toLongOrNull() ?: 0L,
            from.new_deaths?.toLongOrNull() ?: 0L,
            from.total_cases?.toLongOrNull() ?: 0L,
            from.total_deaths?.toLongOrNull() ?: 0L,
            from.total_recovered?.toLongOrNull() ?: 0L
        )
    }

    fun mapToRoom(from: WorldStatsEntity): WorldStatsRoomEntity {
        return WorldStatsRoomEntity(
            from.totalCases,
            from.totalDeaths,
            from.totalRecovered,
            from.newCases,
            from.newDeaths
        )
    }

    fun mapFromRoom(from: WorldStatsRoomEntity) : WorldStatsEntity {
        return WorldStatsEntity(
            from.newCases,
            from.newDeaths,
            from.totalCases,
            from.totalDeaths,
            from.totalRecovered
        )
    }

}