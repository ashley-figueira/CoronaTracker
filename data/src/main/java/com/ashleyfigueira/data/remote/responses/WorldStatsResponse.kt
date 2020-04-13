package com.ashleyfigueira.data.remote.responses

import com.google.gson.annotations.SerializedName

data class WorldStatsResponse(
    @SerializedName("new_cases") val new_cases: String?,
    @SerializedName("new_deaths") val new_deaths: String?,
    @SerializedName("statistic_taken_at") val statistic_taken_at: String?,
    @SerializedName("total_cases") val total_cases: String?,
    @SerializedName("total_deaths") val total_deaths: String?,
    @SerializedName("total_recovered") val total_recovered: String?
)