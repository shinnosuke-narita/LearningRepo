package com.example.androidtestpractice.chapter2

import android.os.Build.VERSION_CODES.S
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.Before

class WeatherForecastTest {
    private lateinit var weatherForecast: WeatherForecast
    private lateinit var formatter: WeatherFormatter
    private lateinit var recorder: WeatherRecorder
    private lateinit var satellite: Satellite

    @Before
    fun setUp() {
        formatter = spy(WeatherFormatter())
        satellite = Satellite()
        recorder = WeatherRecorder()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        weatherForecast.recordCurrentWeather(37.897, -122.0000)
        verify(formatter, times(1)).format(any())
    }
}