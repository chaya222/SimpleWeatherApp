package com.example.simpleweatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.simpleweatherapp.feature.InfoViewState
import com.example.simpleweatherapp.feature.WeatherViewModel
import com.example.simpleweatherapp.feature.data.WeatherResponse
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class WeatherAppJunitTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherRepo: WeatherRepo

    lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.weatherViewModel = WeatherViewModel(this.weatherRepo)
    }


    @Test
    fun getInfo_positiveResponse(){
        Mockito.`when`(this.weatherRepo.getWeatherForecast(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Single.just(WeatherResponse()))

        val observer = mock(Observer::class.java) as Observer<InfoViewState>
        this.weatherViewModel.viewState.observeForever(observer)
        this.weatherViewModel.getWeatherForecast(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())
        assertNotNull(this.weatherViewModel.viewState.value)
        assertEquals(false,this.weatherViewModel.viewState.value!!.isError)
        assertEquals(false,this.weatherViewModel.viewState.value!!.isLoading)
        assertEquals(true,this.weatherViewModel.viewState.value!!.showData)
    }


    @Test
    fun getInfo_failureResponse(){
        Mockito.`when`(this.weatherRepo.getWeatherForecast(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Single.error(Throwable("Network Error")))

        val observer = mock(Observer::class.java) as Observer<InfoViewState>
        this.weatherViewModel.viewState.observeForever(observer)
        this.weatherViewModel.getWeatherForecast(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())
        assertNotNull(this.weatherViewModel.viewState.value)
        assertEquals(true,this.weatherViewModel.viewState.value!!.isError)
        assertEquals(false,this.weatherViewModel.viewState.value!!.isLoading)
        assertEquals(false,this.weatherViewModel.viewState.value!!.showData)
    }


}