package com.example.simpleweatherapp.feature.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data") val data: Data = Data()
) {
    data class Data(
        @SerializedName("weather") val weather: List<ForecastDay> = ArrayList()
    )
}

data class ForecastDay(
    @SerializedName("date") var date : String = "",
    @SerializedName("avgtempC") var avgTemp: String = "",
    @SerializedName("hourly") var weatherDescList: List<WeatherDescription> = ArrayList()
) {
    data class WeatherDescription(
        @SerializedName("weatherDesc") var weatherDescValueList: List<WeatherDescriptionValue> = ArrayList()
    ) {
        data class WeatherDescriptionValue(
            @SerializedName("value") val String: String? = ""
        )
    }
}




