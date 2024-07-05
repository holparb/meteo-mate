package com.holparb.meteomate.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): LocationData?
}