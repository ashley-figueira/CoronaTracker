package com.ashleyfigueira.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity

@Dao
interface WorldStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorldStats(worldStats: WorldStatsRoomEntity)

    @Query("SELECT * FROM world_stats ORDER BY totalCases DESC LIMIT 1")
    suspend fun getWorldStats(): WorldStatsRoomEntity
}