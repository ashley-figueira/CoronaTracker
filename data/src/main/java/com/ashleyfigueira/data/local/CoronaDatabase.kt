package com.ashleyfigueira.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashleyfigueira.data.local.entities.CountryStatsRoomEntity
import com.ashleyfigueira.data.local.entities.WorldStatsRoomEntity

@Database(entities = [
    WorldStatsRoomEntity::class,
    CountryStatsRoomEntity::class
], version = 2, exportSchema = false)
abstract class CoronaDatabase : RoomDatabase() {
    abstract fun worldStatsDao(): WorldStatsDao
    abstract fun countryStatsDao(): CountryStatsDao
}