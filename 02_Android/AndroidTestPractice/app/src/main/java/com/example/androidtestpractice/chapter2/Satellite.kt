package com.example.androidtestpractice.chapter2

open class Satellite {
    open fun getWeather(): Weather {
        return Weather.RAINY
    }

    open fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.SUNNY
    }
}

class StubSatellite(val anyWeather: Weather) : Satellite() {
    override fun getWeather(): Weather {
        return anyWeather
    }
}

