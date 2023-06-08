package com.ahmetkeskin.porte.feature.detail.presentation

import com.ahmetkeskin.porte.BaseViewModel
import com.ahmetkeskin.porte.feature.home.data.PrefType
import com.ahmetkeskin.porte.feature.home.domain.SetAndGetToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val setAndGetToken: SetAndGetToken
) : BaseViewModel() {
    fun setToken(token: String) {
        setAndGetToken.execute(SetAndGetToken.Params(PrefType.SET, token))
    }

    fun getToken(token: String) =
        setAndGetToken.execute(SetAndGetToken.Params(PrefType.GET))

}