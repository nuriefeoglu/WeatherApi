package com.nuriefeoglu.weatherapi.navigation

import android.app.Activity
import android.content.Intent
import com.nuriefeoglu.weatherapi.activity.WeatherDetailActivity
import com.nuriefeoglu.weatherapi.activity.WeatherResultActivity
import com.nuriefeoglu.weatherapi.network.WeatherResponseResult

object NavigationHelper {

    fun navigateToWeatherResultActivity(activity: Activity, city: String) {
        val intent = Intent(activity, WeatherResultActivity::class.java)
        intent.putExtra(WeatherResultActivity.CITY_PARAMETER, city)
        activity.startActivity(intent)
    }

    /*fun navigateToWeatherDetailActivity(
        activity: Activity,
        date: String,
        day: String,
        description: String,
        degree: String,
        icon: String
    ) {
        val intent = Intent(activity, WeatherDetailActivity::class.java)
        intent.putExtra(WeatherResultActivity.DATE_PARAMETER, date)
        intent.putExtra(WeatherResultActivity.DAY_PARAMETER, day)
        intent.putExtra(WeatherResultActivity.DESCRIPTION_PARAMETER, description)
        intent.putExtra(WeatherResultActivity.DEGREE_PARAMETER, degree)
        intent.putExtra(WeatherResultActivity.ICON_PARAMETER, icon)
        activity.startActivity(intent)
    }*/

    fun navToWeatherDetail(activity: Activity, model: WeatherResponseResult?, city: String) {
        val intent = Intent(activity, WeatherDetailActivity::class.java)
        intent.putExtra(WeatherDetailActivity.MODEL_PARAMETER, model)
        intent.putExtra(WeatherDetailActivity.CITY_PARAMETER, city)
        activity.startActivity(intent)
    }

}