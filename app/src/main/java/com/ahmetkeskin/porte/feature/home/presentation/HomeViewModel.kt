package com.ahmetkeskin.porte.feature.home.presentation

import com.ahmetkeskin.porte.base.BaseViewModel
import com.ahmetkeskin.porte.feature.home.data.PrefType
import com.ahmetkeskin.porte.feature.home.domain.SetAndGetToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setAndGetToken: SetAndGetToken
) : BaseViewModel() {
    fun setToken(token: String) {
        setAndGetToken.execute(this, SetAndGetToken.Params(PrefType.SET, token))
    }

    fun getToken() =
        setAndGetToken.execute(this, SetAndGetToken.Params(PrefType.GET))

}