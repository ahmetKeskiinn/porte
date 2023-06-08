package com.ahmetkeskin.porte.di

import android.content.Context


class PrefManager(context: Context) {

    companion object {
        private const val PREFS = "prefs"
        private const val TOKEN = "token"
    }

    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    var token: String?
        get() = prefs.get(TOKEN)
        set(value) {
            prefs.set(TOKEN, value)
        }
}