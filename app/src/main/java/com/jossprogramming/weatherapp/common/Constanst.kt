package com.jossprogramming.weatherapp.common

object Constanst {
    const val API_KEY = "45db9ecce8a5a09fedfae7d5cb765f57"
    const val URL_BASE = "https://api.weatherstack.com/"
    const val LOCATION_ERROR_MESSAGE = "No location found. Verify the precise permission is granted."
    const val UNKONWN_ERROR_MESSAGE = "Unknown error"
    const val AVAILABLE_FOR_UK_CANADA_US_ZIP_CODE_ERROR = "Temperature available for UK/Canada/US ZIP code"
    const val UNEXPECTED_ERROR = "An unexpected error occurred"
    const val FAKE_RESPONSE = """{
    "request": {
        "type": "City",
        "query": "New York, United States of America",
        "language": "en",
        "unit": "m"
    },
    "location": {
        "name": "New York",
        "country": "United States of America",
        "region": "New York",
        "lat": "40.714",
        "lon": "-74.006",
        "timezone_id": "America/New_York",
        "localtime": "2019-09-07 08:14",
        "localtime_epoch": 1567844040,
        "utc_offset": "-4.0"
    },
    "current": {
        "observation_time": "12:14 PM",
        "temperature": 13,
        "weather_code": 113,
        "weather_icons": [
            "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png"
        ],
        "weather_descriptions": [
            "Sunny"
        ],
        "astro": {
            "sunrise": "06:31 AM",
            "sunset": "05:47 PM",
            "moonrise": "06:56 AM",
            "moonset": "06:47 PM",
            "moon_phase": "Waxing Crescent",
            "moon_illumination": 0
        },
        "air_quality": {
            "co": "468.05",
            "no2": "32.005",
            "o3": "55",
            "so2": "7.4",
            "pm2_5": "6.66",
            "pm10": "6.66",
            "us-epa-index": "1",
            "gb-defra-index": "1"
        },
        "wind_speed": 0,
        "wind_degree": 349,
        "wind_dir": "N",
        "pressure": 1010,
        "precip": 0,
        "humidity": 90,
        "cloudcover": 0,
        "feelslike": 13,
        "uv_index": 4,
        "visibility": 16
    }
}"""
}