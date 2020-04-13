package com.ashleyfigueira.data.di

import android.content.Context
import android.preference.PreferenceManager
import androidx.room.Room
import com.ashleyfigueira.data.BuildConfig
import com.ashleyfigueira.data.R
import com.ashleyfigueira.data.local.CoronaDatabase
import com.ashleyfigueira.data.local.CountryStatsDao
import com.ashleyfigueira.data.local.WorldStatsDao
import com.ashleyfigueira.data.remote.ApiService
import com.ashleyfigueira.domain.di.ApplicationContext
import com.ashleyfigueira.domain.di.PerApplication
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @PerApplication
    fun provideWorldStatsDao(coronaDatabase: CoronaDatabase): WorldStatsDao = coronaDatabase.worldStatsDao()

    @Provides
    @PerApplication
    fun provideCountryStatsDao(coronaDatabase: CoronaDatabase): CountryStatsDao = coronaDatabase.countryStatsDao()

    @Provides
    @PerApplication
    fun provideRoomDatabase(@ApplicationContext context: Context): CoronaDatabase =
        Room.databaseBuilder(context, CoronaDatabase::class.java, "corona-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @PerApplication
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @PerApplication
    fun provideCoronaRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    @PerApplication
    fun provideOkHttpClient(headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", BuildConfig.API_KEY)

            val actualRequest = request.build()
            chain.proceed(actualRequest)
        }
    }
}