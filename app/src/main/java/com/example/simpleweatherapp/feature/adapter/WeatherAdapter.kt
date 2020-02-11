package com.example.simpleweatherapp.feature.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.feature.data.ForecastDay
import com.example.simpleweatherapp.utils.makeInVisible
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var weatherDataList = ArrayList<ForecastDay>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_layout_weather,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val itemForcast = weatherDataList.get(position)

        holder.tvDayTitle.text = getDayTitle(itemForcast.date)
        holder.tvTemperature.text = getTempString(itemForcast)

        holder.ivWeatherDesc.setImageDrawable(
            holder.itemView.context.getDrawable(
                getWeatherIcon(
                    itemForcast
                )
            )
        )
        holder.tvWeatherDesc.text = getWeatherDescString(itemForcast)

        if (weatherDataList.size.minus(1) == position)
            holder.vwDivider.makeInVisible()

    }

    @SuppressLint("DefaultLocale")
    fun getWeatherIcon(itemForecastDay: ForecastDay): Int {
        val desc = itemForecastDay.weatherDescList.get(0).weatherDescValueList.get(0).value
        if (desc.isNullOrEmpty()) return R.drawable.ic_clear
        return when {
            desc.toLowerCase().contains("clear") -> R.drawable.ic_clear
            desc.toLowerCase().contains("storm") -> R.drawable.ic_storm
            desc.toLowerCase().contains("rain") -> R.drawable.ic_rain
            desc.toLowerCase().contains("cloud") -> R.drawable.ic_clouds
            desc.toLowerCase().contains("sun") -> R.drawable.ic_sun
            else -> R.drawable.ic_clear
        }
    }

    @SuppressLint("DefaultLocale")
    fun getWeatherDescString(itemForecastDay: ForecastDay): String {
        val desc = itemForecastDay.weatherDescList.get(0).weatherDescValueList.get(0).value
        if (desc.isNullOrEmpty()) return "Clear"
        return when {
            desc.toLowerCase().contains("clear") -> "Clear"
            desc.toLowerCase().contains("storm") -> "Storm"
            desc.toLowerCase().contains("rain") -> "Rain"
            desc.toLowerCase().contains("cloud") -> "Clouds"
            desc.toLowerCase().contains("sun") -> "Sunny"
            else -> "Clear"
        }
    }


    fun getTempString(itemForecastDay: ForecastDay): String {
        return "${itemForecastDay.avgTemp}${(0x00B0).toChar()}/${itemForecastDay.maxTemp}${(0x00B0).toChar()}"
    }

    fun getDayTitle(date: String): String {
        var calendar = Calendar.getInstance()
        val todayDay = SimpleDateFormat("yyyy-MM-dd").format(calendar.timeInMillis)

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = SimpleDateFormat("yyyy-MM-dd").format(calendar.timeInMillis)

        val otherDay =
            SimpleDateFormat("dd MMM yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))
        return when (date) {
            todayDay -> "Today"
            tomorrow -> "Tomorrow"
            else -> otherDay
        }
    }


    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDayTitle = view.findViewById<TextView>(R.id.tvDayTitle)
        val tvTemperature = view.findViewById<TextView>(R.id.tvTemperature)
        val vwDivider = view.findViewById<View>(R.id.vwDivider)
        val ivWeatherDesc = view.findViewById<ImageView>(R.id.ivWeatherDesc)
        val tvWeatherDesc = view.findViewById<TextView>(R.id.tvWeatherDesc)
    }

    fun updateList(list: List<ForecastDay>) {
        weatherDataList.clear()
        weatherDataList.addAll(list)
    }

}