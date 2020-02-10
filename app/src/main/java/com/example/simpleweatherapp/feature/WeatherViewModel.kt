package com.example.simpleweatherapp.feature

import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import javax.inject.Inject

class WeatherViewModel @Inject constructor(var repo : WeatherRepo) : ViewModel(){

}