package com.nuriefeoglu.weatherapi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {

        const val BASE_URL = "https://api.collectapi.com/weather/"

    }

    @GET("getWeather")
    fun getWeather(
        @Query("data.lang")
        language: String,
        @Query("data.city")
        city: String
    ): Call<WeatherResponse>

}