package com.example.pos_demo_mvvm.utils.token

class DefaultTokenProvider: TokenProvider {
    @Volatile
    private var token: String = ""

    override fun getToken(): String {
        return token
    }

    override fun refreshToken(): String {
        token = "new_token"
        return token
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun revokeToken() {
        this.token = ""
    }
}