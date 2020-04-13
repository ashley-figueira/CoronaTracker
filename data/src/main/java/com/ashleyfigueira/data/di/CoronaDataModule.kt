package com.ashleyfigueira.data.di

import com.ashleyfigueira.data.CoronaRepositoryImpl
import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.di.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class CoronaDataModule {

    @Binds
    @PerApplication
    abstract fun provideCoronaRepository(coronaRepository: CoronaRepositoryImpl): CoronaRepository
}