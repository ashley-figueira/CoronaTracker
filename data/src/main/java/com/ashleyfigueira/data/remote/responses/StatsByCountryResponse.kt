package com.ashleyfigueira.data.remote.responses

import com.google.gson.annotations.SerializedName

data class StatsByCountryResponse(
    @SerializedName("countries_stat") val countries_stat: List<CountriesStat?>?,
    @SerializedName("statistic_taken_at") val statistic_taken_at: String?
) {
    data class CountriesStat(
        @SerializedName("active_cases") val active_cases: String?,
        @SerializedName("cases") val cases: String?,
        @SerializedName("country_name") val country_name: String?,
        @SerializedName("deaths") val deaths: String?,
        @SerializedName("new_cases") val new_cases: String?,
        @SerializedName("new_deaths") val new_deaths: String?,
        @SerializedName("region") val region: String?,
        @SerializedName("serious_critical") val serious_critical: String?,
        @SerializedName("total_cases_per_1m_population") val total_cases_per_1m_population: String?,
        @SerializedName("total_recovered") val total_recovered: String?
    )
}