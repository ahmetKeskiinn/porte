package com.ahmetkeskin.porte.feature.detail.data.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord? = null,
    @SerializedName("weather")
    val weather: List<WeatherItem?>? = null,
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("main")
    val main: WeatherMain? = null,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("wind")
    val wind: Wind? = null,
    @SerializedName("rain")
    val rain: Rain? = null,
    @SerializedName("clouds")
    val clouds: Cloud? = null,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("cod")
    val cod: Int? = null,
    @SerializedName("sys")
    val Sys: Sys? = null
    )