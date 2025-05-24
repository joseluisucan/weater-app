package com.jossprogramming.weatherapp.network.services.ApiService

import com.jossprogramming.weatherapp.network.models.WeatherModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    //EXAMPLE REQUEST
    // https://api.weatherstack.com/current?access_key={PASTE_YOUR_API_KEY_HERE}&query=40.7831,-73.9712


    /*query = 99501	*/
    @GET("current")
    suspend fun getWeatherByZip(
        @Query("access_key") accessKey: String,
        @Query("query") zipCode: String
    ): Response<WeatherModelResponse>

    /*query = 40.7831,-73.9712*/
    @GET("current")
    suspend fun getWeatherByCoordinates(
        @Query("access_key") accessKey: String,
        @Query("query") latLon: String // e.g. "19.4326,-99.1332"
    ): Response<WeatherModelResponse>
}