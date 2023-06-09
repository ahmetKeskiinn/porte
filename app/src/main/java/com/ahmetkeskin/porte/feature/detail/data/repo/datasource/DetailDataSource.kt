package com.ahmetkeskin.porte.feature.detail.data.repo.datasource

import com.ahmetkeskin.porte.feature.detail.data.DetailApiService
import javax.inject.Inject

class DetailDataSource @Inject constructor(
    private val apiService: DetailApiService
) {
    suspend fun getWeather(lat: Double, lon: Double, appid: String) = apiService.getWeather(lat, lon, appid)
}