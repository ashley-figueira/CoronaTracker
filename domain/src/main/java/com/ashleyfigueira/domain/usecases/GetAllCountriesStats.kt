package com.ashleyfigueira.domain.usecases

import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllCountriesStats @Inject constructor(
    private val coronaRepository: CoronaRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke() = withContext(dispatcherProvider.io()) { coronaRepository.getAllCountryStats() }
}