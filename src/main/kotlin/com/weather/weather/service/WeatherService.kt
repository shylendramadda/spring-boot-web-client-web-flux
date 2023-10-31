package com.weather.weather.service

import com.weather.weather.dto.Daily
import com.weather.weather.dto.DailyReport
import com.weather.weather.model.Properties
import com.weather.weather.model.WeatherReport
import com.weather.weather.utils.AppUtils
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WeatherService {
    val client = WebClient.create("https://api.weather.gov/gridpoints/MLB/33,70/forecast")

    fun getTodayWeatherReport(): Mono<DailyReport> {
        return client.get()
            .retrieve()
            .bodyToMono(WeatherReport::class.java)
            .flatMap { transformData(it.properties) }
    }

    private fun transformData(properties: Properties): Mono<DailyReport> {
        val response = DailyReport()
        val dailyList = arrayListOf<Daily>()

        properties.periods.forEach { period ->
            val startTime = period.startTime
            val date = startTime.substring(0, 10)
            if (AppUtils.isDateStringToday(date, "yyyy-MM-dd")) {
                val temperatureInF = period.temperature
                val temperatureInC = AppUtils.getCelsiusFromFahrenheit(temperatureInF)
                val daily = Daily(period.name, temperatureInC, period.shortForecast)
                dailyList.add(daily)
            }
        }
        response.daily = dailyList
        return Mono.just(response)
    }

}