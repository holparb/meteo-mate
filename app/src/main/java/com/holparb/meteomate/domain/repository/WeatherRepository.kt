package com.holparb.meteomate.domain.repository

import com.holparb.meteomate.domain.util.Resource
import com.holparb.meteomate.domain.util.WeatherError
import com.holparb.meteomate.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo, WeatherError>
}