package com.example.simpleweatherapp.di.module

import com.example.simpleweatherapp.feature.ShowWeatherForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributWeatherActivity() : ShowWeatherForecastActivity

}