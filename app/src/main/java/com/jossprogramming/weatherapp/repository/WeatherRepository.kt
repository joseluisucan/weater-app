package com.jossprogramming.weatherapp.repository

import com.jossprogramming.weatherapp.common.Constanst
import com.jossprogramming.weatherapp.network.models.WeatherModelResponse
import com.jossprogramming.weatherapp.network.services.ApiService.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {

    suspend fun getWeatherByZip(zipCode: String): Result<WeatherModelResponse> {
        return try {
            val response = weatherService.getWeatherByZip(Constanst.API_KEY, zipCode)
            val body = response.body()
            val temperature = body?.current?.temperature

            if (response.isSuccessful && body != null && temperature != null && temperature != 0) {
                Result.success(body)
            } else {
                Result.failure(Exception("Temperature not Available"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("An unexpected error occurred"))
        }
    }

    suspend fun getWeatherByCoordinates(latLon: String): Result<WeatherModelResponse> {
        return try {
            val response = weatherService.getWeatherByCoordinates(Constanst.API_KEY, latLon)
            val body = response.body()
            val temperature = body?.current?.temperature

            if (response.isSuccessful && body != null && temperature != null && temperature != 0) {
                Result.success(body)
            } else {
                Result.failure(Exception("Temperature not Available"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("An unexpected error occurred"))
        }
    }
}
