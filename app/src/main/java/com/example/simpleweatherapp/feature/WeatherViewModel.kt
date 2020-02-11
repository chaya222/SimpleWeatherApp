package com.example.simpleweatherapp.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.feature.data.WeatherData
import com.example.simpleweatherapp.feature.data.WeatherResponse
import com.example.simpleweatherapp.feature.repo.WeatherRepo
import com.example.simpleweatherapp.utils.AndroidDisposable
import javax.inject.Inject

class WeatherViewModel @Inject constructor(var repo : WeatherRepo) : ViewModel(){
    private var disposable = AndroidDisposable()
    private val _viewState = MutableLiveData<InfoViewState>()
    private val _infoLD = MutableLiveData<WeatherResponse>()


    val viewState: LiveData<InfoViewState>
        get() = _viewState

    val info: LiveData<WeatherResponse>
        get() = _infoLD


    init {
        _viewState.value = InfoViewState(isLoading = true)
    }

    fun setLoadingState() {
        _viewState.value = InfoViewState(isLoading = true)
    }

    fun getWeatherForecast(latitude: String, longitude: String) {
        disposable.add(
            repo.getWeatherForecast(latitude, longitude)
                .subscribe(
                    {
                        _infoLD.value = it
                        _viewState.value?.let {
                            val newState =
                                this.viewState.value?.copy(isLoading = false, isError = false, showData = true)
                            _viewState.postValue(newState)
                        }
                    },
                    {
                        _viewState.value = viewState.value?.copy(isLoading = false, isError = true, showData = false)
                    }
                )
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}