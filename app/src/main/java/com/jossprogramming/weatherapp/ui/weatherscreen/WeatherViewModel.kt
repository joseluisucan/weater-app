package com.jossprogramming.weatherapp.ui.weatherscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossprogramming.weatherapp.common.Constanst.LOCATION_ERROR_MESSAGE
import com.jossprogramming.weatherapp.common.Constanst.UNKONWN_ERROR_MESSAGE
import com.jossprogramming.weatherapp.repository.LocationRepository
import com.jossprogramming.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeatherByZip(zip: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            repository.getWeatherByZip(zip)
                .onSuccess { _uiState.value = WeatherUiState.Success(it)  }
                .onFailure { _uiState.value = WeatherUiState.Error(it.message ?: "Unknown error") }
        }
    }

    fun loadWeatherFromLocation() {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            val location = locationRepository.getLastKnownLocation()

            if (location != null) {
                //val fakeCoords = "19.4326,-99.1332"
                val latLon = "${location.latitude},${location.longitude}"

                //get coordinates from repository
                val result = repository.getWeatherByCoordinates(latLon)

                _uiState.value = result.fold(
                    onSuccess = { WeatherUiState.Success(it) },
                    onFailure = { WeatherUiState.Error(it.message ?: UNKONWN_ERROR_MESSAGE) }
                )
            } else {
                _uiState.value = WeatherUiState.Error(LOCATION_ERROR_MESSAGE)
            }
        }
    }

    fun setErrorByPermission(){
        _uiState.value = WeatherUiState.Error(LOCATION_ERROR_MESSAGE)
    }
}