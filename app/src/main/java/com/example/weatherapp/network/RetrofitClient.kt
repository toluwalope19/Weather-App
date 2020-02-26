package com.example.weatherapp.network

import com.example.weatherapp.util.Util
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


    private fun provideRetrofit(): Retrofit {
        val requestInterceptor = Interceptor{

            val url = it.request()
                .url()
                .newBuilder()
                .addQueryParameter("access_key",Util.API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor it.proceed(request)
        }

         val httpClient = OkHttpClient.Builder()
             .addInterceptor(requestInterceptor)
             .connectTimeout(60,TimeUnit.SECONDS)
             .build()



        return Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(httpClient)
            .build()

    }






    fun provideMovieApi(): ApixuApi {
        return provideRetrofit().create(ApixuApi::class.java)
    }

}