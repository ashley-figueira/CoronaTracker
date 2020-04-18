package com.ashleyfigueira.coronatracker.main

import androidx.lifecycle.viewModelScope
import com.ashleyfigueira.coronatracker.base.BaseViewModel
import com.ashleyfigueira.coronatracker.base.ScreenState
import com.ashleyfigueira.domain.common.CoronaError
import com.ashleyfigueira.domain.common.CoronaResult
import com.ashleyfigueira.domain.common.DispatcherProvider
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import com.ashleyfigueira.domain.usecases.GetAllCountriesStats
import com.ashleyfigueira.domain.usecases.GetWorldStatsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getWorldStatsUseCase: GetWorldStatsUseCase,
    private val getAllCountriesStats: GetAllCountriesStats,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<ScreenState<UiModel>>() {

    init {
        viewModelScope.launch(dispatcherProvider.ui()) {
            _screenState.value = ScreenState.loading(true)

            val worldStatsResult = getWorldStatsUseCase()
            val countryStatsResult = getAllCountriesStats()

            _screenState.value = ScreenState.loading(false)
            //Success case
            if (worldStatsResult is CoronaResult.Success && countryStatsResult is CoronaResult.Success) {
                val countryStatsList = countryStatsResult.data
                    .filter { it.countryName.isNotBlank() }
                    .sortedBy { it.totalCases }
                    .reversed()

                _screenState.value = ScreenState.success(UiModel(worldStatsResult.data, countryStatsList))
            //Some error handling
            } else if (worldStatsResult is CoronaResult.Failure && countryStatsResult is CoronaResult.Failure) {
                _screenState.value = when {
                    worldStatsResult.error is CoronaError.Offline
                        && countryStatsResult.error is CoronaError.Offline -> ScreenState.noInternet()
                    else -> ScreenState.empty()
                }
            } else {
                _screenState.value = ScreenState.empty()
            }
        }
    }

}

data class UiModel(
    val worldStats: WorldStatsEntity,
    val countryStats: List<CountryStatsEntity>
)
