package com.ashleyfigueira.coronatracker.di

import android.content.Context
import com.ashleyfigueira.coronatracker.common.CoronaApplication
import com.ashleyfigueira.coronatracker.common.DispatcherProviderImpl
import com.ashleyfigueira.data.di.CoronaDataModule
import com.ashleyfigueira.data.di.DataModule
import com.ashleyfigueira.domain.CoronaRepository
import com.ashleyfigueira.domain.common.DispatcherProvider
import com.ashleyfigueira.domain.di.ApplicationContext
import com.ashleyfigueira.domain.di.PerApplication
import dagger.*

@Component(modules = [
    CoronaDataModule::class,
    DataModule::class,
    AppModule::class
])
@PerApplication
interface AppComponent {

    @ApplicationContext fun exposeContext(): Context

    fun exposeDispatcherProvider(): DispatcherProvider

    fun exposeCoronaRepository(): CoronaRepository

    fun inject(target: CoronaApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }
}

@Module
abstract class AppModule {

    @Binds
    @PerApplication
    abstract fun dispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}