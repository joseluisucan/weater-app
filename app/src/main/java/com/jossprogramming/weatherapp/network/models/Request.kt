package com.jossprogramming.weatherapp.network.models

data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)