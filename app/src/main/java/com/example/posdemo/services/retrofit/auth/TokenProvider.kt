package com.example.posdemo.retrofit.auth

interface TokenProvider {
    fun getToken(): String
    fun refreshToken(): String?
    fun setToken(token: String)

    fun revokeToken()
}