package com.holparb.meteomate.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.holparb.meteomate.data.location.DefaultLocationTracker
import com.holparb.meteomate.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationProviderModule {

    @Provides
    @Singleton
    fun proviesLocationClient(application: Application) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationTrackerModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        locationTracker: DefaultLocationTracker
    ): LocationTracker
}