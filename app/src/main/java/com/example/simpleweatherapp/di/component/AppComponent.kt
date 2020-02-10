package com.example.simpleweatherapp.di.component

import com.example.simpleweatherapp.WeatherApp
import com.example.simpleweatherapp.di.module.ActivityModule
import com.example.simpleweatherapp.di.module.ApiModule
import com.example.simpleweatherapp.di.module.ContextModule
import com.example.simpleweatherapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    ActivityModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class,
    ApiModule::class,
    ContextModule::class
    ]
)
interface AppComponent  : AndroidInjector<WeatherApp>{

    @Component.Builder
    interface  Builder{
        @BindsInstance
        fun application(app: WeatherApp): Builder

        fun build():AppComponent
    }


}