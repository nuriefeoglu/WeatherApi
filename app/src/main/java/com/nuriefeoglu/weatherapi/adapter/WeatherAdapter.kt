package com.nuriefeoglu.weatherapi.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.nuriefeoglu.weatherapi.R
import com.nuriefeoglu.weatherapi.network.WeatherResponseResult
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather_day.*

class WeatherAdapter(private val listener: (WeatherResponseResult?) -> Unit) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherData = arrayListOf<WeatherResponseResult?>()

    fun setWeatherData(data: List<WeatherResponseResult?>?) {
        weatherData.clear()
        data?.let {
            weatherData.addAll(it)
        }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weather_day, parent, false)
        )
    }

    override fun getItemCount() = weatherData.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        weatherData[position]?.let {
            holder.bind(it)
        }
        holder.itemView.setOnClickListener {
            listener.invoke(weatherData[position])

        }
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {


        fun bind(model: WeatherResponseResult) {

            txtDay?.text = model.day
            txtDegree?.text = model.getDegreeText()
            txtDesc?.text = model.description

            GlideToVectorYou.init()
                .with(imgWeather.context)
                .load(Uri.parse(model.icon), imgWeather)

        }

        override val containerView: View?
            get() = itemView

    }


}
