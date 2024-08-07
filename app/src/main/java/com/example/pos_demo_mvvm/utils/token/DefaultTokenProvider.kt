package com.example.pos_demo_mvvm.utils.token

import android.content.Context

class DefaultTokenProvider(private val context: Context) : TokenProvider {
    private val tokenManager = TokenManager(context)

    override fun getToken(): String {
        val token = tokenManager.getToken()
        return token?: ""
    }

    override fun refreshToken(): String? {
        val newToken =  "new_token"
        tokenManager.saveToken(newToken)
        return newToken
    }

    override fun setToken(token: String) {
        tokenManager.saveToken(token)
    }

    override fun revokeToken() {
        tokenManager.clearToken()
    }
}