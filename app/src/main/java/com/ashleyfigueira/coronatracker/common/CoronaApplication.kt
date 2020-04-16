package com.ashleyfigueira.coronatracker.common

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ashleyfigueira.coronatracker.di.AppComponent
import com.ashleyfigueira.coronatracker.di.DaggerAppComponent
import timber.log.Timber

class CoronaApplication : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        val Context.appComponent
            get() = (applicationContext as CoronaApplication).appComponent
    }
}