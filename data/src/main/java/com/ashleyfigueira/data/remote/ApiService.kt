package com.ashleyfigueira.data.remote

import com.ashleyfigueira.data.remote.responses.StatsByCountryResponse
import com.ashleyfigueira.data.remote.responses.WorldStatsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("coronavirus/cases_by_country.php")
    suspend fun getCountryWiseCases(): StatsByCountryResponse

    @GET("coronavirus/worldstat.php")
    suspend fun getWorldStats(): WorldStatsResponse
}