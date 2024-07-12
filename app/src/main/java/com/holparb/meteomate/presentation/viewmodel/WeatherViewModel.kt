package com.holparb.meteomate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.holparb.meteomate.domain.location.LocationTracker
import com.holparb.meteomate.domain.repository.WeatherRepository
import com.holparb.meteomate.domain.util.Resource
import com.holparb.meteomate.domain.util.WeatherError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    private fun loadWeatherInfo() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, error = null)
            }
            when(val locationResult = locationTracker.getCurrentLocation()) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = locationResult.error.message
                        )
                    }
                }
                is Resource.Success -> {
                    when(val weatherResult = repository.getWeatherData(lat = locationResult.data.lat, long = locationResult.data.long)) {
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = null,
                                    weatherInfo = weatherResult.data
                                )
                            }
                        }
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    weatherInfo = null,
                                    error = (weatherResult.error as? WeatherError.NetworkError)?.message
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: WeatherEvent) {
        when(event) {
            is WeatherEvent.LoadWeatherInfo -> {
                loadWeatherInfo()
            }
        }
    }
}