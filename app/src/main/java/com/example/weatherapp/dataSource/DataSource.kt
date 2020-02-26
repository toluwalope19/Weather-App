package com.example.weatherapp.dataSource

import androidx.lifecycle.LiveData
import com.example.weatherapp.model.WeatherResponse

interface DataSource {

    fun getWeather(): LiveData<WeatherResponse>

    fun refreshWeather()

    fun saveWeather(weatherResponse: WeatherResponse)

    fun clearWeather()

}