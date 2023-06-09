package com.ahmetkeskin.porte.feature.detail.data

import com.ahmetkeskin.porte.feature.detail.data.response.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DetailApiService {

    @Headers("Accept: application/json")
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("appid") appid: String?,
    ): WeatherResponse
}