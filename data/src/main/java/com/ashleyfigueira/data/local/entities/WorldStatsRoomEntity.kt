package com.ashleyfigueira.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "world_stats")
data class WorldStatsRoomEntity(
    @PrimaryKey val totalCases: Long,
    val totalDeaths: Long,
    val totalRecovered: Long,
    val newCases: Long,
    val newDeaths: Long
)