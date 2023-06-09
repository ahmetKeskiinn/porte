package com.ahmetkeskin.porte.feature.detail.data.repo

import com.ahmetkeskin.porte.feature.detail.data.response.WeatherResponse
import io.reactivex.Observable

interface DetailRepository {
    suspend fun getWeather(lat: Double, lon: Double, appid: String): WeatherResponse
}