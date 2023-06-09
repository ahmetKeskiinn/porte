package com.ahmetkeskin.porte.feature.detail.presentation

import com.ahmetkeskin.porte.feature.detail.data.response.city.City

interface CityClickListener {
    fun onCityClicked(city: City)
}