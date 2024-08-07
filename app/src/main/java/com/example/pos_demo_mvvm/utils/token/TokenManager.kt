package com.example.pos_demo_mvvm.utils.token

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class TokenManager(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        preferences.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        Log.d("auth_token",preferences.getString("auth_token", null).toString() )
        return preferences.getString("auth_token", null)
    }

    fun clearToken() {
        preferences.edit().remove("auth_token").apply()
    }
}