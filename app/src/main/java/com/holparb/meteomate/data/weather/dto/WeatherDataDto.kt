package com.holparb.meteomate.data.weather.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataDto (
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "wind_speed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "relative_humidity_2m")
    val humidities: List<Double>
)