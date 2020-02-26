package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.model.Weather
import com.example.weatherapp.util.Result

interface Repository {

    suspend fun getWeather(): LiveData<Result<List<Weather>>>


}