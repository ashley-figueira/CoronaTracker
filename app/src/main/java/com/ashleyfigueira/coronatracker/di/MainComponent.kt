package com.ashleyfigueira.coronatracker.di

import android.app.Activity
import android.content.Context
import com.ashleyfigueira.coronatracker.MainActivity
import com.ashleyfigueira.domain.di.ActivityContext
import com.ashleyfigueira.domain.di.PerActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    FragmentProvider::class,
    ViewModelProvider::class
], dependencies = [AppComponent::class])
@PerActivity
interface MainComponent {

    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance fun activity(activity: Activity): Builder

        @BindsInstance fun context(@ActivityContext context: Context): Builder

        fun core(appComponent: AppComponent): Builder

        fun build(): MainComponent
    }
}