package com.example.horoscopo.utils

import android.content.Context

class SessionManager(private val context: Context) {

    private val prefs= context.getSharedPreferences("horoscope:session", Context.MODE_PRIVATE)

    fun setFavorite (horoscopeID: String)
    {
        val editor = prefs.edit()
        editor.putString("favorite", horoscopeID)
        editor.apply()
    }

    fun getFavorite(): String
    {
        return prefs.getString("favorite","")!!

    }

    fun isFavorite(horoscopeID: String): Boolean
    {
        return horoscopeID == getFavorite()

    }
}