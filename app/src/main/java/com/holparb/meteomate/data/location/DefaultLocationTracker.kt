package com.holparb.meteomate.data.location

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.holparb.meteomate.domain.location.LocationData
import com.holparb.meteomate.domain.location.LocationTracker
import javax.inject.Inject

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {
    override suspend fun getCurrentLocation(): LocationData? {
        TODO("Not yet implemented")
    }
}