package com.jossprogramming.weatherapp.network.models

data class Astro(
    val moon_illumination: Double,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)