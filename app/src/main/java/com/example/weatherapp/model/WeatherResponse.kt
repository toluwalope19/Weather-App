package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val current: Weather,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)