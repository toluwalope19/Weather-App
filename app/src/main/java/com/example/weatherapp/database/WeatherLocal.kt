package com.example.weatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.WeatherDomain
import com.google.gson.annotations.SerializedName


@Entity
data class WeatherLocal (

    @PrimaryKey
    val id : Int = 0,
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

fun List<WeatherLocal>.asDomainModel(): List<WeatherDomain> {
    return map {
        WeatherDomain(
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
    }
}