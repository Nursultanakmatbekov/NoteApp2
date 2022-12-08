package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference:SharedPreferences

    fun init(context: Context){
        sharedPreference = context.getSharedPreferences("shared",Context.MODE_PRIVATE)
    }

    var isShow: Boolean
        get() = sharedPreference.getBoolean("isShow",false)
        set(value) = sharedPreference.edit().putBoolean("isShow",value).apply()
}