package com.example.simpleweatherapp.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.feature.data.ForecastDay
import com.example.simpleweatherapp.feature.data.WeatherData
import com.example.simpleweatherapp.feature.data.WeatherResponse
import com.example.simpleweatherapp.utils.makeInVisible
import com.example.simpleweatherapp.utils.makeVisible
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

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
        holder.tvDayTitle.text = getdayTitle(itemForcast.date)

        if (weatherDataList.size.minus(1) == position)
            holder.vwDivider.makeInVisible()

    }

    fun getdayTitle(date: String): String {
        val calendar = Calendar.getInstance()
        val todayDay = SimpleDateFormat("yyyy-MM-dd").format(calendar.timeInMillis)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val tomorrow = SimpleDateFormat("yyyy-MM-dd").format(calendar.timeInMillis)

        if (date == todayDay)
            return "Today"
        else if (date == tomorrow)
            return "Tomorrow"
        else return date
    }


    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDayTitle = view.findViewById<TextView>(R.id.tvDayTitle)
        val vwDivider = view.findViewById<View>(R.id.vwDivider)
    }

    fun updateList(list: List<ForecastDay>) {
        weatherDataList.clear()
        weatherDataList.addAll(list)
    }

}