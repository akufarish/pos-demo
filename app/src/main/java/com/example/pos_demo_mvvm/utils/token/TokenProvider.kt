package com.example.pos_demo_mvvm.utils.token

interface TokenProvider {
    fun getToken(): String
    fun refreshToken(): String?
    fun setToken(token: String)

    fun revokeToken()
}