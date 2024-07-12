package com.holparb.meteomate.presentation.viewmodel

sealed interface WeatherEvent {
    data object LoadWeatherInfo: WeatherEvent
}