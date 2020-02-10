package com.example.simpleweatherapp.di.module

import android.content.Context
import com.example.simpleweatherapp.WeatherApp
import dagger.Module
import dagger.Provides


@Module
class ContextModule{
    @Provides
    fun provideContext(weatherApp: WeatherApp):Context{
        return weatherApp.applicationContext
    }
}