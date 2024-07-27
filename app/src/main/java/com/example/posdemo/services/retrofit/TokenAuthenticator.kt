package com.example.posdemo.retrofit

import com.example.posdemo.retrofit.auth.TokenProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val tokenProvider: TokenProvider): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newToken = tokenProvider.refreshToken() ?: return null

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }
}