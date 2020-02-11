package com.example.simpleweatherapp.feature.repo

import com.example.simpleweatherapp.api.ApiInterface
import com.example.simpleweatherapp.feature.data.WeatherResponse
import com.example.simpleweatherapp.utils.AppRxSchedulers
import com.example.simpleweatherapp.utils.Constants.Companion.WEATHER_API_KEY
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepo @Inject constructor(
  var  api : ApiInterface,
  var rxSchedulers: AppRxSchedulers
){


    fun getWeatherForecast(latitude: String, longitude: String): Single<WeatherResponse> {
        return api.getWeatherForecast(WEATHER_API_KEY, "$latitude,$longitude", "7","json")
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidThread())
    }

}