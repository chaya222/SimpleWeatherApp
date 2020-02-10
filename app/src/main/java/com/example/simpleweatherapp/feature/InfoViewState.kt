package com.example.simpleweatherapp.feature

data class InfoViewState(
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var showData: Boolean = false
)
