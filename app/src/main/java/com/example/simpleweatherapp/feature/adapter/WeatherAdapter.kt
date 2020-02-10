package com.example.simpleweatherapp.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.feature.data.WeatherData

class WeatherAdapter () : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){
    private var weatherDataList = ArrayList<WeatherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
     return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_weather,parent,false))
    }

    override fun getItemCount(): Int {
         return weatherDataList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

    }


    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    fun updateList(list : ArrayList<WeatherData>){
        weatherDataList.clear()
        for(i in list){
            weatherDataList.add(WeatherData().apply {

            })
        }

        notifyDataSetChanged()
    }
}