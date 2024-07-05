package com.holparb.meteomate.domain.util

sealed class Resource<out S, out E> {
    data class Success<out S>(val data: S): Resource<S, Nothing>()
    data class Error<out E>(val error: E): Resource<Nothing, E>()
}

sealed class WeatherError {
    data class NetworkError(val message: String?) : WeatherError()
    object LocalDataError: WeatherError()
}