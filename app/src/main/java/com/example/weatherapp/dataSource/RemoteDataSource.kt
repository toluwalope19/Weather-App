package com.example.weatherapp.dataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApixuApi
import com.example.weatherapp.util.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(private val api: ApixuApi, private val compositeDisposable: CompositeDisposable) : DataSource {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState

    private val _apiResponse = MutableLiveData<WeatherResponse>()
    val apiResponse
    get() = _apiResponse

    override fun getWeather(): LiveData<WeatherResponse> {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                api.getWeather()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _apiResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.d("networks", it.message.toString())
                        }
                    )
            )

        }catch (e: Exception){
            Log.d("networks", e.message.toString())
        }
        return apiResponse
    }

    override fun refreshWeather() {
        _apiResponse.value = getWeather().value

    }

    override fun saveWeather(weatherResponse: WeatherResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearWeather() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}