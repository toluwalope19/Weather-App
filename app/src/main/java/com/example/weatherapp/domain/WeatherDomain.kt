package com.example.weatherapp.domain

data class  WeatherDomain (
    val location: String,
    val description: List<String>,
    val temperature: Long,
    val windSpeed: Long,
    val windDir: String,
    val precipitation: Long,
    val humidity: Long,
    val visibility: Long,
    val icon: List<String>
)