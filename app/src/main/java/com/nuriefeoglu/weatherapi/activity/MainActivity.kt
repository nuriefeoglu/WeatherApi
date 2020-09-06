package com.nuriefeoglu.weatherapi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nuriefeoglu.weatherapi.databinding.ActivityMainBinding
import com.nuriefeoglu.weatherapi.helper.Helper
import com.nuriefeoglu.weatherapi.navigation.NavigationHelper

class MainActivity : AppCompatActivity() {


    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSearchCity?.setOnClickListener {
            val city = binding?.edtCity?.text?.toString()
            if (!city.isNullOrBlank()) {
                NavigationHelper.navigateToWeatherResultActivity(this, city)
            } else {
                Helper.showToast(this, "Şehir adı boş olamaz")
            }
        }
    }

}