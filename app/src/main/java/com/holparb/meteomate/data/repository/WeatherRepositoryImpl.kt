package com.holparb.meteomate.data.repository

import android.util.Log
import com.holparb.meteomate.data.datasource.remote.WeatherApi
import com.holparb.meteomate.data.weather.mappers.toWeatherInfo
import com.holparb.meteomate.domain.repository.WeatherRepository
import com.holparb.meteomate.domain.util.Resource
import com.holparb.meteomate.domain.util.WeatherError
import com.holparb.meteomate.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo, WeatherError> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat = lat, long = long).toWeatherInfo()
            )
        }
        catch(e: Exception) {
            Log.e(this::class.simpleName, e.localizedMessage ?: "Unknown error")
            Resource.Error(error = WeatherError.NetworkError("Couldn't fetch weather data, please try again!"))
        }
    }
}