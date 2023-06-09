package com.ahmetkeskin.porte.base

import com.ahmetkeskin.porte.BaseViewModel

abstract class BaseUseCase<ReturnType, Params> {
    abstract fun execute(viewModel: BaseViewModel, input: Params?): ReturnType?
}