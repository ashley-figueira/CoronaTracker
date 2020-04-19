package com.ashleyfigueira.data

import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity
import com.ashleyfigueira.data.remote.responses.WorldStatsResponse
import com.ashleyfigueira.domain.common.Mapper
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import org.joda.time.DateTime
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class WorldStatsEntityMapper @Inject constructor() : Mapper<WorldStatsResponse, WorldStatsEntity>() {

    override fun mapFrom(from: WorldStatsResponse): WorldStatsEntity {
        return WorldStatsEntity(
            formatToLong(from.new_cases),
            formatToLong(from.new_deaths),
            formatToLong(from.total_cases),
            formatToLong(from.total_deaths),
            formatToLong(from.total_recovered)
        )
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

    fun mapToRoom(from: WorldStatsEntity): WorldStatsRoomEntity {
        return WorldStatsRoomEntity(
            from.totalCases,
            from.totalDeaths,
            from.totalRecovered,
            from.newCases,
            from.newDeaths,
            DateTime.now().millis
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