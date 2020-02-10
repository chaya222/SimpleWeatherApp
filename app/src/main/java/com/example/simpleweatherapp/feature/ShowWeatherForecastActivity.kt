package com.example.simpleweatherapp.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.feature.adapter.WeatherAdapter
import com.example.simpleweatherapp.feature.data.WeatherData
import com.example.simpleweatherapp.utils.slideUp
import kotlinx.android.synthetic.main.activity_show_weather_forecast_main.*

class ShowWeatherForecastActivity : AppCompatActivity() {

    private var weatherAdapter: WeatherAdapter = WeatherAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather_forecast)

        setRecyclerView()
    }


    private fun setRecyclerView() {
        clickMe.setOnClickListener {

        }
        val linearLayoutManager = LinearLayoutManager(this)
        rvWeatherForecast.layoutManager=linearLayoutManager
        rvWeatherForecast.adapter=weatherAdapter
        weatherAdapter.updateList(getWeatherList())
        rvWeatherForecast.slideUp()


    }

    private fun getWeatherList() : ArrayList<WeatherData>{
        val wList = ArrayList<WeatherData>()

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })

        wList.add(WeatherData().apply {

        })
        return wList
    }
}
