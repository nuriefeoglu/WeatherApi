package com.nuriefeoglu.weatherapi.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    init {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {

                val request = it.request()
                    .newBuilder()
                    .addHeader(
                        "authorization",
                        "apikey 4Xe6lGctq0Mm3ayjBLPBaB:6ZgiblGHRIaE0gXS5HVbNu"
                    ).build()
                it.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit?.create()

    }

    fun getWeather(city: String, callback : (List<WeatherResponseResult?>?) -> Unit){

        apiService?.getWeather("tr",city)
            ?.enqueue(object : Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("---data",t.localizedMessage?:"Yarra yedik")
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    response.body()?.let {
                        callback.invoke(it.result)
                    }
                }


            })

    }


}