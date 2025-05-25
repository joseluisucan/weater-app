package com.jossprogramming.weatherapp.ui.weatherscreen

import com.jossprogramming.weatherapp.network.models.WeatherModelResponse

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val data: WeatherModelResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
    object Idle : WeatherUiState()
}