package com.jossprogramming.weatherapp.network.models

data class Current(
    val air_quality: AirQuality,
    val astro: Astro,
    val cloudcover: Double,
    val feelslike: Double,
    val humidity: Double,
    val observation_time: String,
    val precip: Double,
    val pressure: Double,
    val temperature: Double,
    val uv_index: Double,
    val visibility: Double,
    val weather_code: Double,
    val weather_descriptions: List<String>,
    val weather_icons: List<String>,
    val wind_degree: Double,
    val wind_dir: String,
    val wind_speed: Double
)