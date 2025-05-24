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
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en respuesta: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWeatherByCoordinates(latLon: String): Result<WeatherModelResponse> {
        return try {
            val response = weatherService.getWeatherByCoordinates(Constanst.API_KEY, latLon)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en respuesta: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
