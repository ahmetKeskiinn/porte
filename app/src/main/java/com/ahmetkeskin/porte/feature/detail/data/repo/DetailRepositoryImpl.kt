package com.ahmetkeskin.porte.feature.detail.data.repo

import com.ahmetkeskin.porte.feature.detail.data.repo.datasource.DetailDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataSource: DetailDataSource
) : DetailRepository {
    override suspend fun getWeather(lat: Double, lon: Double, appid: String) =
        dataSource.getWeather(lat, lon, appid)
}