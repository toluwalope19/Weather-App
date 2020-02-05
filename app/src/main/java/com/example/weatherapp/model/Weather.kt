package com.example.weatherapp.model

data class Weather (
    val location: String,
    val temperature: Long,
    val windSpeed: Long,
    val windDir: String,
    val precipitation: Long,
    val humidity: Long,
    val visibility: Long
)