package com.holparb.meteomate.di

import com.holparb.meteomate.data.datasource.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://open-meteo.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}