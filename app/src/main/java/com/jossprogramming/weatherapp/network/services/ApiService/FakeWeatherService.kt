package com.jossprogramming.weatherapp.network.services.ApiService

import com.google.gson.Gson
import com.jossprogramming.weatherapp.common.Constanst
import com.jossprogramming.weatherapp.network.models.WeatherModelResponse
import retrofit2.Response

class FakeWeatherService: WeatherService  {
    override suspend fun getWeatherByZip(
        accessKey: String,
        zipCode: String
    ): Response<WeatherModelResponse> {
        val json = Constanst.FAKE_RESPONSE
        val gson = Gson()
        val response = gson.fromJson(json, WeatherModelResponse::class.java)
        return Response.success(response)
    }

    override suspend fun getWeatherByCoordinates(
        accessKey: String,
        latLon: String
    ): Response<WeatherModelResponse> {
        val json = Constanst.FAKE_RESPONSE
        val gson = Gson()
        val response = gson.fromJson(json, WeatherModelResponse::class.java)
        return Response.success(response)
    }
}