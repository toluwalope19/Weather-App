package com.example.weatherapp.dataSource

import androidx.lifecycle.LiveData
import com.example.weatherapp.model.Weather

interface DataSource {

    fun getWeather(): LiveData<Weather>

    fun refreshWeather()

    fun saveWeather(weather: Weather)

    fun clearWeather()

}