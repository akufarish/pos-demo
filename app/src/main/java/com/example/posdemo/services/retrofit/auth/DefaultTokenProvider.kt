package com.example.posdemo.retrofit.auth

class DefaultTokenProvider : TokenProvider {
    @Volatile
    private var token: String = ""

    override fun getToken(): String {
        return token
    }

    override fun refreshToken(): String? {
        // Logic to refresh the token
        // This should include the network call to refresh the token
        token = "new_token"  // Replace with actual token refresh logic
        return token
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun revokeToken() {
        this.token = ""
    }
}