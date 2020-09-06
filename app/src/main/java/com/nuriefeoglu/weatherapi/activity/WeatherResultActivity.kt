package com.nuriefeoglu.weatherapi.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.nuriefeoglu.weatherapi.R
import com.nuriefeoglu.weatherapi.adapter.WeatherAdapter
import com.nuriefeoglu.weatherapi.databinding.ActivityWeatherResultBinding
import com.nuriefeoglu.weatherapi.navigation.NavigationHelper
import com.nuriefeoglu.weatherapi.network.NetworkHelper
import kotlinx.android.synthetic.main.activity_weather_result.*

class WeatherResultActivity : AppCompatActivity() {

    companion object {
        const val CITY_PARAMETER = "city"
    }

    private var binding: ActivityWeatherResultBinding? = null

    private var city: String? = null
    private val weatherAdapter = WeatherAdapter {
        city?.let { it1 -> NavigationHelper.navToWeatherDetail(this, it, it1) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        city = intent?.getStringExtra(WeatherDetailActivity.CITY_PARAMETER)?.capitalize()

        getCityWeather()
        initActionBar()
        initRecyclerView()


    }

    private fun getCityWeather() {
        city?.let {
            NetworkHelper.getWeather(it) { result ->
                binding?.progressBar?.visibility = View.GONE
                binding?.txtNoResult?.isVisible = result.isNullOrEmpty()
                weatherAdapter.setWeatherData(result)
            }
        }
    }

    private fun initRecyclerView() {
        binding?.rvWeatherResult?.adapter = weatherAdapter
        binding?.rvWeatherResult?.layoutManager = LinearLayoutManager(this)
    }

    private fun initActionBar() {
        supportActionBar?.title = "$city için hava durumu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}