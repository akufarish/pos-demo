package com.example.pos_demo_mvvm.data.repository

import com.example.pos_demo_mvvm.data.model.auth.login.LoginRequest
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.model.auth.register.RegisterRequest
import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: ApiEndPoint) {
    suspend fun doLogin(payload: LoginRequest): Response<LoginResponses> {
        return api.login(payload)
    }

    suspend fun doRegister(payload: RegisterRequest): Response<LoginResponses> {
        return api.register(payload)
    }
}