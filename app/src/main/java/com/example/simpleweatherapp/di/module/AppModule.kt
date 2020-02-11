package com.example.simpleweatherapp.di.module

import com.example.simpleweatherapp.api.ApiInterface
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import com.example.simpleweatherapp.utils.AppRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule{

    @Singleton
    @Provides
    fun provideWetherRepo(api : ApiInterface,rxSchedulers: AppRxSchedulers) : WeatherRepo = WeatherRepo(api = api,rxSchedulers = rxSchedulers)

}