package com.example.weatherapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastTabBinding
import com.example.weatherapp.model.ForecastWeather

class ForecastAdapter(): RecyclerView.Adapter<ForecastAdapter.ForecastHolder>() {

    val weathers = listOf<ForecastWeather>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ForecastTabBinding.inflate(inflater)

        return ForecastHolder(binding)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {

            val weather = weathers[position]
            holder.bind(weather)
    }

    class ForecastHolder(private val binding: ForecastTabBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(item: ForecastWeather){
                binding.weather = item
                binding.executePendingBindings()
            }

    }
}