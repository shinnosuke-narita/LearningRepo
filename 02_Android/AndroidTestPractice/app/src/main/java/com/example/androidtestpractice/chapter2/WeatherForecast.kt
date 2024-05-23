package com.example.androidtestpractice.chapter2

class WeatherForecast(
    private val satellite: Satellite,
    private val recorder: WeatherRecorder,
    private val formatter: WeatherFormatter) {

    fun shouldBringUmbrella(): Boolean {
        return when(satellite.getWeather()) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun shouldBringUmbrella(latitude: Double, longitude: Double): Boolean {
        val weather = satellite.getWeather(latitude, longitude)
        return when(weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather(latitude: Double, longitude: Double) {
        val weather = satellite.getWeather(latitude, longitude)
        val description = formatter.format(weather)
        recorder.record(Record(description))
    }
}

enum class Weather {
    SUNNY, CLOUDY, RAINY
}