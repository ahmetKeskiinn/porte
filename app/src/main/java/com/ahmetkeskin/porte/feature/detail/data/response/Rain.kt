package com.ahmetkeskin.porte.feature.detail.data.response

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val rainValue: Double? = null
)