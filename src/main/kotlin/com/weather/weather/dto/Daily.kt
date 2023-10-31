package com.weather.weather.dto

data class Daily(
    val dayName: String,
    val tempHighCelsius: Long,
    val forecastBlurp: String,
)