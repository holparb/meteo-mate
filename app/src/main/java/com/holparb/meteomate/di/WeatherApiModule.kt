package com.holparb.meteomate.di

import com.holparb.meteomate.data.datasource.remote.WeatherApi
import com.holparb.meteomate.data.repository.WeatherRepositoryImpl
import com.holparb.meteomate.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import dagger.Binds
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
object WeatherApiModule {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}

