package com.example.posdemo.retrofit.auth

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preferences.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return preferences.getString("auth_token", null)
    }

    fun clearToken() {
        preferences.edit().remove("auth_token").apply()
    }
}