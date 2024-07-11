package com.holparb.meteomate.domain.location

import com.holparb.meteomate.domain.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<LocationData, LocationException>

    class LocationException(message: String): Exception()
}