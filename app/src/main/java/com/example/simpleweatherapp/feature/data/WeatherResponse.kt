package com.example.simpleweatherapp.feature.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val forecast: WeatherForecast? = WeatherForecast()
) {

    data class WeatherForecast(
        @SerializedName("forecastday") val forecast: List<ForecastDay> = ArrayList()
    ){
        data class ForecastDay(
            @SerializedName("avgtempC") var avgTemp: String = "",
            @SerializedName("hourly") val day: List<HourForecast?> = ArrayList()
        ) {
            data class HourForecast(
                @SerializedName("weatherDesc") val weatherDesc: List<WeatherDescription> = ArrayList()
            )
        }
    }
}

data class WeatherDescription(
    @SerializedName("value") val String: String? = ""
)
