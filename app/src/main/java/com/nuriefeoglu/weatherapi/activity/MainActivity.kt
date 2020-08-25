package com.nuriefeoglu.weatherapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nuriefeoglu.weatherapi.R
import com.nuriefeoglu.weatherapi.helper.Helper
import com.nuriefeoglu.weatherapi.navigation.NavigationHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearchCity.setOnClickListener {
            val city = edtCity?.text?.toString()
            if (!city.isNullOrBlank()){
                NavigationHelper.navigateToWeatherResultActivity(this,city)
            } else {
                Helper.showToast(this,"Şehir adı boş olamaz")
            }
        }
    }

}