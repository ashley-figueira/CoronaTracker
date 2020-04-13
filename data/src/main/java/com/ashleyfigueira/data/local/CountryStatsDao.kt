package com.ashleyfigueira.data.local

import androidx.room.*
import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity

@Dao
interface CountryStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryStats(countryStats: List<CountryStatsRoomEntity>)

    @Query("SELECT * FROM country_stats")
    suspend fun getCountryStats(): List<CountryStatsRoomEntity>
}