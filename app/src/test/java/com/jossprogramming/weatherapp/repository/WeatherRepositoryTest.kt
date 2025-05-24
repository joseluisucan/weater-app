package com.jossprogramming.weatherapp.repository

import com.jossprogramming.weatherapp.network.services.ApiService.FakeWeatherService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository
    private val fakeService = FakeWeatherService()

    @Before
    fun setUp() {
        repository = WeatherRepository(fakeService)
    }

    @Test
    fun `getWeatherByZip returns success with fake data`() = runTest {
        // Arrange
        val fakeService = FakeWeatherService()
        val repository = WeatherRepository(fakeService)

        // Act
        val result = repository.getWeatherByZip("10001")

        // Assert
        assertTrue(result.isSuccess)
        val weather = result.getOrNull()
        assertNotNull(weather)
        assertEquals("New York", weather?.location?.name)
        assertEquals(13, weather?.current?.temperature)
        assertEquals("Sunny", weather?.current?.weather_descriptions?.firstOrNull())
    }
}