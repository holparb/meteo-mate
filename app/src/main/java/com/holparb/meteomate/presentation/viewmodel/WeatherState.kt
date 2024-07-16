package com.holparb.meteomate.presentation.viewmodel

import com.holparb.meteomate.domain.weather.WeatherInfo

enum class Status {
    Loading, Loaded, Error
}

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val status: Status = Status.Loading,
    val errorMessage: String? = null
)
