package com.nuriefeoglu.weatherapi.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.nuriefeoglu.weatherapi.databinding.ItemWeatherDayBinding
import com.nuriefeoglu.weatherapi.network.WeatherResponseResult

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
        val binding = ItemWeatherDayBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
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

    class WeatherViewHolder(private val binding: ItemWeatherDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: WeatherResponseResult) {
            print("***MODEL -> $model")
            binding.txtDay.text = model.day
            binding.txtDegree.text = model.getDegreeText()
            binding.txtDesc.text = model.description

            GlideToVectorYou.init()
                .with(binding.imgWeather.context)
                .load(Uri.parse(model.icon), binding.imgWeather)

        }

    }


}
