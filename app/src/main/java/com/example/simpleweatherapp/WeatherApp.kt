package com.example.simpleweatherapp


import com.example.simpleweatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApp : DaggerApplication(){


    init {
        instance=this
    }

    companion object{
        private var instance : WeatherApp?=null
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
         return DaggerAppComponent
             .builder()
             .application(this)
             .build()


    }

}