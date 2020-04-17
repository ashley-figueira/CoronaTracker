package com.ashleyfigueira.coronatracker.di

import com.ashleyfigueira.coronatracker.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideMainFragment(): MainFragment
}