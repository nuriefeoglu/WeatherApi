package com.nuriefeoglu.weatherapi.activity

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nuriefeoglu.weatherapi.R
import com.nuriefeoglu.weatherapi.databinding.ActivityWeatherDetailBinding
import com.nuriefeoglu.weatherapi.network.WeatherResponseResult
import kotlinx.android.synthetic.main.activity_weather_detail.*
import java.util.*

class WeatherDetailActivity : AppCompatActivity() {

    companion object {
        const val MODEL_PARAMETER = "model_parameter"
        const val CITY_PARAMETER = "city"
    }

    private var city: String? = null
    private var binding: ActivityWeatherDetailBinding? = null

    private fun setBoldSpannable(text: String): SpannableString {
        val f = text.split(" ").firstOrNull()
        val spannableContent = SpannableString(text)
        spannableContent.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            f?.length ?: 0,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        return spannableContent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val model = intent.getParcelableExtra<WeatherResponseResult>(MODEL_PARAMETER)


        city = intent?.getStringExtra(CITY_PARAMETER)?.capitalize()
        initActionBar()

        model.let {
            binding?.txtDay?.text = it?.day
            binding?.txtDegree?.text = it?.degree
            binding?.txtHumidity?.text = it?.getHumidityText()?.let { it1 -> setBoldSpannable(it1) }
            binding?.txtMinDegree?.text = it?.getDegreeMinText()
            val wind = "Wind 12m/s"
            binding?.txtWind?.text = setBoldSpannable(wind)

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initActionBar() {
        supportActionBar?.title = "$city için hava durumu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}