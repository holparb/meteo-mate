package com.holparb.meteomate.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holparb.meteomate.R
import com.holparb.meteomate.domain.weather.WeatherData
import com.holparb.meteomate.domain.weather.WeatherInfo
import com.holparb.meteomate.domain.weather.WeatherType
import com.holparb.meteomate.presentation.ui.theme.DeepBlue
import com.holparb.meteomate.presentation.viewmodel.WeatherState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier) {
    state.weatherInfo?.currentWeatherData?.let { weatherData ->
        Card(
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        weatherData.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = "weather type",
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${weatherData.temperature}°C",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = weatherData.weatherType.weatherDesc,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = weatherData.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource( id = R.drawable.ic_pressure )
                    )
                    WeatherDataDisplay(
                        value = weatherData.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource( id = R.drawable.ic_wind )
                    )
                    WeatherDataDisplay(
                        value = weatherData.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource( id = R.drawable.ic_drop )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    val weatherData = WeatherData(
        time = LocalDateTime.now(),
        temperature = 15.0,
        pressure = 22.0,
        windSpeed = 150.0,
        humidity = 25.0,
        weatherType = WeatherType.ClearSky
    )
    WeatherCard(state = WeatherState(weatherInfo = WeatherInfo(emptyMap(), weatherData)), backgroundColor = DeepBlue)
}