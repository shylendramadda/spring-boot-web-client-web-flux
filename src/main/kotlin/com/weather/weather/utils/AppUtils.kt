package com.weather.weather.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object AppUtils {

    fun getCelsiusFromFahrenheit(temperatureInF: Long): Long {
        return ((temperatureInF - 32) * 5) / 9
    }

    fun isDateStringToday(dateString: String, dateFormat: String): Boolean {
        // Define the format of the date string (e.g., "yyyy-MM-dd")
        val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
        // Parse the date string into a LocalDate
        val parsedDate = LocalDate.parse(dateString, dateFormatter)
        // Get the current date
        val currentDate = LocalDate.now()
        // Compare the parsed date with the current date
        return parsedDate.isEqual(currentDate)
    }
}