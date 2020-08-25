package com.nuriefeoglu.weatherapi.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt

data class WeatherResponse(
    val success: Boolean?,
    val city: String?,
    val result: List<WeatherResponseResult?>?
)
@Parcelize
data class WeatherResponseResult(
    val date: String?,
    val day: String?,
    val description: String?,
    val degree: String?,
    val icon: String?,
    val min: String?,
    val max: String?,
    val status: String?,
    val humidity: String?
):Parcelable {
    fun getDegreeText(): String {
        return "${degree?.toFloatOrNull()?.roundToInt()}°C"
    }
    fun getDegreeMinText(): String{
        return "${min?.toFloatOrNull()?.roundToInt()}°C"
    }
    fun getHumidityText(): String {
        return "Humidity ${humidity?.toFloatOrNull()?.roundToInt()}%"
    }
}


