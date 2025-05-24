package com.jossprogramming.weatherapp.network.models

data class WeatherModelResponse(
    val current: Current,
    val location: Location,
    val request: Request
)