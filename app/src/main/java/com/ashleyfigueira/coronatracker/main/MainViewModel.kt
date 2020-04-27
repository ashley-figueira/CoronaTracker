package com.ashleyfigueira.coronatracker.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.ashleyfigueira.coronatracker.base.BaseViewModel
import com.ashleyfigueira.coronatracker.base.ScreenState
import com.ashleyfigueira.domain.common.*
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

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        viewModelScope.launch(dispatcherProvider.ui()) {
            showLoadingSpinner()

            val worldStatsResult = getWorldStatsUseCase()
            val countryStatsResult = getAllCountriesStats()

            hideLoadingSpinner()

            _screenState.value = when {
                worldStatsResult.isSuccess() && countryStatsResult.isSuccess() -> {
                    val countryStatsList = countryStatsResult.getData()
                        .filter { it.countryName.isNotBlank() }
                        .sortedBy { it.totalCases }
                        .reversed()

                    ScreenState.success(UiModel(worldStatsResult.getData(), countryStatsList))
                }
                worldStatsResult.isSuccess() && countryStatsResult.isFailure() -> {
                    ScreenState.success(UiModel(worldStatsResult.getData(), emptyList()))
                }
                worldStatsResult.isFailure() -> {
                    when (worldStatsResult.getError()) {
                        is CoronaError.Offline -> ScreenState.noInternet()
                        else -> ScreenState.empty()
                    }
                } else -> ScreenState.empty()
            }
        }
    }
}

data class UiModel(
    val worldStats: WorldStatsEntity,
    val countryStats: List<CountryStatsEntity>
)
