package com.holparb.meteomate.data.location

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.holparb.meteomate.domain.location.LocationData
import com.holparb.meteomate.domain.location.LocationTracker
import com.holparb.meteomate.domain.util.Resource
import com.holparb.meteomate.domain.util.hasLocationPermission
import com.holparb.meteomate.domain.util.isGpsEnabled
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Resource<LocationData, LocationTracker.LocationError> {
        if(!application.hasLocationPermission()) {
            return Resource.Error(LocationTracker.LocationError("Location permission is not granted!"))
        }
        if(!application.isGpsEnabled()) {
            return Resource.Error(LocationTracker.LocationError("GPS is not enabled"))
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        val locationData = LocationData(result.latitude, result.longitude)
                        cont.resume(Resource.Success(locationData))
                    }
                    else {
                        cont.resume(Resource.Error(LocationTracker.LocationError("Location fetch was not successful")))
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { locationResult ->
                    if(locationResult != null) {
                        val locationData = LocationData(locationResult.latitude, locationResult.longitude)
                        cont.resume(Resource.Success(locationData))
                    }
                    else {
                        cont.resume(Resource.Error(LocationTracker.LocationError("Location fetch was not successful")))
                    }
                }
                addOnFailureListener {
                    cont.resume(Resource.Error(LocationTracker.LocationError("Location fetch was not successful")))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }

    class LocationError
}