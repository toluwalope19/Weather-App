package com.example.weatherapp.model

import android.os.Parcelable
import com.example.weatherapp.database.WeatherLocal
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather (
    @Transient
    val id : Int = 0,
    @SerializedName("name")
    val location: String,
    @SerializedName("weather_descriptions")
    val description: List<String>,
    @SerializedName("temperature")
    val temperature: Long,
    @SerializedName("wind_speed")
    val windSpeed: Long,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("precip")
    val precipitation: Long,
    @SerializedName("humidity")
    val humidity: Long,
    @SerializedName("visibility")
    val visibility: Long,
    @SerializedName("weather_icons")
    val icon: List<String>
): Parcelable

data class NetworkWeatherContainer(val weather: List<Weather>)

fun NetworkWeatherContainer.asDatabaseModel(): Array<WeatherLocal> {
    return weather.map {
            WeatherLocal(
                id = it.id,
                location = it.location,
                description = it.description,
                temperature = it.temperature,
                windSpeed = it.windSpeed,
                windDir = it.windDir,
                precipitation = it.precipitation,
                humidity = it.humidity,
                visibility = it.visibility,
                icon = it.icon
            )

    }.toTypedArray()
}