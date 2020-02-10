package com.example.simpleweatherapp.di.module

import com.example.simpleweatherapp.api.ApiInterface
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule{

    @Singleton
    @Provides
    fun provideWetherRepo(api : ApiInterface) : WeatherRepo = WeatherRepo(api = api)

}