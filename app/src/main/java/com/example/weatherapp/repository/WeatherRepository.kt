package com.example.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.dataSource.RemoteDataSource
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApixuApi
import com.example.weatherapp.util.NetworkState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

class WeatherRepository(private val api: ApixuApi) {

    lateinit var remoteDataSource: RemoteDataSource

    fun getWeather(compositeDisposable: CompositeDisposable): LiveData<Weather>{
        remoteDataSource = RemoteDataSource(api,compositeDisposable)

        remoteDataSource.getWeather()

        return remoteDataSource.apiResponse
    }

    fun getWeatherState(): LiveData<NetworkState>{
        return remoteDataSource.networkState
    }

    fun refreshWeather(compositeDisposable: CompositeDisposable): LiveData<Weather>{
        return getWeather(compositeDisposable)
    }



}