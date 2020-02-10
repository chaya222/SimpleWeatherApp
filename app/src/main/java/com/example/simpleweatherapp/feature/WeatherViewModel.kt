package com.example.simpleweatherapp.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.feature.data.WeatherData
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import com.example.simpleweatherapp.utils.AndroidDisposable
import javax.inject.Inject

class WeatherViewModel @Inject constructor(var repo : WeatherRepo) : ViewModel(){
    
}