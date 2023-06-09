package com.ahmetkeskin.porte.feature.detail.presentation

import com.ahmetkeskin.porte.base.BaseViewModel
import com.ahmetkeskin.porte.di.PrefManager
import com.ahmetkeskin.porte.feature.detail.domain.GetWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val weather: GetWeather,
    private val prefManager: PrefManager
) : BaseViewModel() {
    fun getWeather(lat: Double, lon: Double) = weather.execute(
        this, GetWeather.Params(
            lat = lat,
            lon = lon,
            prefManager.token.toString()
        )
    )

}