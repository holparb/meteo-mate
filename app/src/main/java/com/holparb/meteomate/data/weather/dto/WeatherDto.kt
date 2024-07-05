package com.holparb.meteomate.data.weather.dto

import com.squareup.moshi.Json

data class WeatherDto (
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)