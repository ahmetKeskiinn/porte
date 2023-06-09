package com.ahmetkeskin.porte.base

import android.content.Context
import java.io.IOException

fun getJsonDataFromAsset(context: Context, file: Int): String? {
    val jsonString: String
    try {
        jsonString = context.resources.openRawResource(file).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}