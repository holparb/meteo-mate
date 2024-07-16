package com.holparb.meteomate.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.holparb.meteomate.domain.weather.WeatherData
import com.holparb.meteomate.presentation.viewmodel.WeatherState

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow {
                items(data) { weatherData: WeatherData ->
                    HourlyWeatherDisplay(
                        data = weatherData,
                        modifier = Modifier
                            .height(120.dp)
                            .padding(16.dp)
                    )
                }
            }
        }
    }

}