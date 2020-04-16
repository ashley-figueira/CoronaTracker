package com.ashleyfigueira.coronatracker.di

import androidx.lifecycle.ViewModelProvider
import com.ashleyfigueira.coronatracker.common.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProvider {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}