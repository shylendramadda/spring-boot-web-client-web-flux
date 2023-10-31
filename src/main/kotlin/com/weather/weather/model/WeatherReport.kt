package com.weather.weather.model

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherReport(
    @JsonProperty("@context")
    val context: List<Any>,
    val type: String,
    val geometry: Geometry,
    val properties: Properties,
)

data class Context(
    @JsonProperty("@version")
    val version: String,
    val wx: String,
    val geo: String,
    val unit: String,
    @JsonProperty("@vocab")
    val vocab: String,
)

data class Geometry(
    val type: String,
    val coordinates: List<List<List<Double>>>,
)

data class Properties(
    val updated: String,
    val units: String,
    val forecastGenerator: String,
    val generatedAt: String,
    val updateTime: String,
    val validTimes: String,
    val elevation: Elevation,
    val periods: List<Period>,
)

data class Elevation(
    val unitCode: String,
    val value: Double,
)

data class Period(
    val number: Long,
    val name: String,
    val startTime: String,
    val endTime: String,
    val isDaytime: Boolean,
    val temperature: Long,
    val temperatureUnit: String,
    val temperatureTrend: Any?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitation,
    val dewpoint: Dewpoint,
    val relativeHumidity: RelativeHumidity,
    val windSpeed: String,
    val windDirection: String,
    val icon: String,
    val shortForecast: String,
    val detailedForecast: String,
)

data class ProbabilityOfPrecipitation(
    val unitCode: String,
    val value: Long?,
)

data class Dewpoint(
    val unitCode: String,
    val value: Double,
)

data class RelativeHumidity(
    val unitCode: String,
    val value: Long,
)
