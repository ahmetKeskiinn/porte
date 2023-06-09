package com.ahmetkeskin.porte.feature.home.domain

import com.ahmetkeskin.porte.base.BaseViewModel
import com.ahmetkeskin.porte.base.BaseUseCase
import com.ahmetkeskin.porte.di.PrefManager
import com.ahmetkeskin.porte.feature.home.data.PrefType
import javax.inject.Inject

class SetAndGetToken @Inject constructor(
    val prefManager: PrefManager
) : BaseUseCase<String, SetAndGetToken.Params>() {

    data class Params(
        val key: PrefType?,
        val token: String? = null
    )

    override fun execute(viewModel: BaseViewModel, input: Params?): String? {
        return when (input?.key) {
            PrefType.SET -> {
                prefManager.token = input.token
                null
            }
            PrefType.GET ->
                prefManager.token
            else -> {
                null
            }
        }
    }
}