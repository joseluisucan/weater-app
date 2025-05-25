package com.jossprogramming.weatherapp.repository

import com.jossprogramming.weatherapp.common.Constanst
import com.jossprogramming.weatherapp.common.Constanst.AVAILABLE_FOR_UK_CANADA_US_ZIP_CODE_ERROR
import com.jossprogramming.weatherapp.common.Constanst.UNEXPECTED_ERROR
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

            if (response.isSuccessful && body != null && temperature != null) {
                Result.success(body)
            } else {
                Result.failure(Exception(AVAILABLE_FOR_UK_CANADA_US_ZIP_CODE_ERROR))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception(UNEXPECTED_ERROR))
        }
    }

    suspend fun getWeatherByCoordinates(latLon: String): Result<WeatherModelResponse> {
        return try {
            val response = weatherService.getWeatherByCoordinates(Constanst.API_KEY, latLon)
            val body = response.body()
            val temperature = body?.current?.temperature

            if (response.isSuccessful && body != null && temperature != null) {
                Result.success(body)
            } else {
                Result.failure(Exception(AVAILABLE_FOR_UK_CANADA_US_ZIP_CODE_ERROR))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception(UNEXPECTED_ERROR))
        }
    }
}
