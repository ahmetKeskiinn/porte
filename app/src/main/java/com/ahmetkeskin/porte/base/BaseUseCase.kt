package com.ahmetkeskin.porte.base

abstract class BaseUseCase<ReturnType, Params> {
    abstract fun execute(viewModel: BaseViewModel, input: Params?): ReturnType?
}