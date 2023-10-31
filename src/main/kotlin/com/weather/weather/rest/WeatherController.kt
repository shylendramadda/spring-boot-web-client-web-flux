package com.weather.weather.rest

import com.weather.weather.dto.DailyReport
import com.weather.weather.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/weatherReport")
class WeatherController constructor(@Autowired val weatherService: WeatherService) {

    @GetMapping
    fun getTodayReport(): Mono<DailyReport> {
        return weatherService.getTodayWeatherReport()
    }

}
