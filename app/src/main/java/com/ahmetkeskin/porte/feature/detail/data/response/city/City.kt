package com.ahmetkeskin.porte.feature.detail.data.response.city

import com.google.gson.annotations.SerializedName

data class City(
    val id: Int,
    val name: String,
    @SerializedName("coord")
    val coord: Coordinat? = null
)