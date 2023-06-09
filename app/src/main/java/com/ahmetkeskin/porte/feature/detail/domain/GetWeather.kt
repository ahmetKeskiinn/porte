package com.ahmetkeskin.porte.feature.detail.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.porte.BaseViewModel
import com.ahmetkeskin.porte.base.BaseUseCase
import com.ahmetkeskin.porte.feature.detail.data.repo.DetailRepository
import com.ahmetkeskin.porte.feature.detail.data.repo.datasource.DetailDataSource
import com.ahmetkeskin.porte.feature.detail.data.response.WeatherResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetWeather @Inject constructor(
    private val detailRepository: DetailRepository
) : BaseUseCase<MutableLiveData<WeatherResponse>, GetWeather.Params>() {
    data class Params(
        val lat: Double,
        val lon: Double,
        val appid: String
    )

    override fun execute(viewModel: BaseViewModel,input: Params?): MutableLiveData<WeatherResponse>? {
        return MutableLiveData<WeatherResponse>().apply {
            input?.let {
                viewModel.viewModelScope.launch {
                    value =
                        detailRepository.getWeather(lat = it.lat, lon = input.lon, appid = input.appid)
                }
            }
        }
    }
}