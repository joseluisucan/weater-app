package com.jossprogramming.weatherapp.network.models

data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    val localtime_epoch: Double,
    val lon: String,
    val name: String,
    val region: String,
    val timezone_id: String,
    val utc_offset: String
)