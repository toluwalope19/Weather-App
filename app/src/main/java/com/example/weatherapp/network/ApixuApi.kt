package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.util.Util
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApixuApi {
    @GET("current")
    fun getWeather(@Query("access_key") api: String = Util.API_KEY,
                       @Query("query") location: String = "Lagos"): Single<WeatherResponse>
}