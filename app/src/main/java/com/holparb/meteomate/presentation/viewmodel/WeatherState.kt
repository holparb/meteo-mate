package com.holparb.meteomate.presentation.viewmodel

import com.holparb.meteomate.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
