package com.example.simpleweatherapp.feature

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweatherapp.R
import com.example.simpleweatherapp.base.BaseActivity
import com.example.simpleweatherapp.feature.adapter.WeatherAdapter
import com.example.simpleweatherapp.feature.data.ForecastDay
import com.example.simpleweatherapp.feature.data.WeatherData
import com.example.simpleweatherapp.feature.data.WeatherResponse
import com.example.simpleweatherapp.utils.slideUp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_show_weather_forecast_main.*
import android.location.Geocoder
import com.example.simpleweatherapp.utils.makeInVisible
import com.example.simpleweatherapp.utils.makeVisible
import kotlinx.android.synthetic.main.activity_show_weather_forecast.*
import java.util.*


class ShowWeatherForecastActivity : BaseActivity<WeatherViewModel>() {
    override fun provideLayout(): Int = R.layout.activity_show_weather_forecast

    override fun provideViewModelClass(): Class<WeatherViewModel> = WeatherViewModel::class.java

    private var weatherAdapter: WeatherAdapter = WeatherAdapter()
    private lateinit var rxPermissions: RxPermissions
    private var locationReq: LocationRequest? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var cityName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather_forecast)

        initLocationAccess()
        observerForcastAndUpdateUI()
        observerApiState()
        getViewModel().setLoadingState()


    }

    private fun setTodayTemp(forecastDay: ForecastDay?) {
        forecastDay?.let {
            tvTodayTemp.text = "${it.avgTemp}"
            tvDegree.text = "${(0x00B0).toChar()}"
            tvCity.text = cityName
        }
    }

    private fun observerApiState() {
        getViewModel().viewState.observe(this, Observer {

            if (it.isLoading) {
                incProgress.makeVisible()
                incMainView.makeInVisible()
            }

            if (it.isError) {
                incProgress.makeInVisible()
                incMainView.makeInVisible()
            }

            if (it.showData) {
                incProgress.makeInVisible()
                incMainView.makeVisible()
            }

        })
    }

    private fun observerForcastAndUpdateUI() {
        getViewModel().info.observe(this, Observer {
            it.data.let {
                it.weather.let {
                    if (it.size > 6)
                        setRecyclerView(it.subList(0, 7))
                    setTodayTemp(it[0])
                }

            }


        })
    }

    private fun setRecyclerView(it: List<ForecastDay>) {
        val linearLayoutManager = LinearLayoutManager(this)
        rvWeatherForecast.layoutManager = linearLayoutManager
        rvWeatherForecast.adapter = weatherAdapter
        weatherAdapter.updateList(it)
        rvWeatherForecast.slideUp()

    }


    @SuppressLint("CheckResult")
    private fun initLocationAccess() {
        rxPermissions = RxPermissions(this)
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            rxPermissions
                .request(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .subscribe { granted ->
                    run {
                        if (granted) {
                            startLocationUpdates()
                        }
                    }
                }
        } else {
            startLocationUpdates()
        }

    }


    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationReq = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationSettingsReqBuilder = LocationSettingsRequest.Builder()
        locationSettingsReqBuilder.addLocationRequest(locationReq!!)
        val locationSettingsRequest = locationSettingsReqBuilder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            if (location != null) {

                getViewModel().getWeatherForecast(
                    location.latitude.toString(),
                    location.longitude.toString()
                )
                setLocalityName(location)

            } else {
                Log.d("LOCATION", "Location is null")
            }
        }?.addOnFailureListener { exp ->
            Log.d("LOCATION", "Error trying to get last GPS location")
            exp.printStackTrace()
        }
    }

    private fun setLocalityName(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        cityName =
            geocoder.getFromLocation(location.latitude, location.longitude, 1).get(0).locality
    }
}
