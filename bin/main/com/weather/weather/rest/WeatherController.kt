package com.weather.weather.rest

import com.weather.weather.dto.Daily
import com.weather.weather.dto.DailyReport
import com.weather.weather.model.WeatherReport
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("/weatherReport")
class WeatherController {
    val client = WebClient.create("https://api.weather.gov/gridpoints/MLB/33,70/forecast")

    @GetMapping
    fun getDailyReport(): DailyReport {
        val weatherReportMono = client.get()
            .retrieve()
            .bodyToMono(WeatherReport::class.java)

        val response = DailyReport()
        val dailyList = arrayListOf<Daily>()
        weatherReportMono.subscribe {
            it.properties.periods.forEach { period ->
                val daily = Daily(period.name, period.temperature, period.shortForecast)
                dailyList.add(daily)
            }
            response.daily = dailyList
            return response
        }
    }
