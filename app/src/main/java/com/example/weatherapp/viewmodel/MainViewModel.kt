package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.util.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val weather : LiveData<Weather> by lazy{
        repository.getWeather(compositeDisposable)
    }


    val networkState: LiveData<NetworkState> by lazy {
        repository.getWeatherState()
    }

    val refresh: LiveData<Weather> by lazy {
        repository.refreshWeather(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
