package com.example.simpleweatherapp.api

import com.example.simpleweatherapp.feature.data.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("premium/v1/weather.ashx")
    fun getForecast(@Query(value = "key") key: String, @Query(value = "q") query: String, @Query(value = "num_of_days") days: String,@Query(value = "format") format : String): Single<WeatherResponse>
}