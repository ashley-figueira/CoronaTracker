package com.ashleyfigueira.coronatracker.di

import android.content.Context
import com.ashleyfigueira.coronatracker.common.CoronaApplication
import com.ashleyfigueira.data.di.CoronaDataModule
import com.ashleyfigueira.data.di.DataModule
import com.ashleyfigueira.domain.di.ApplicationContext
import com.ashleyfigueira.domain.di.PerApplication
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    CoronaDataModule::class,
    DataModule::class
])
@PerApplication
interface AppComponent {

    fun inject(target: CoronaApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }
}