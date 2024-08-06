package com.example.pos_demo_mvvm.data.server

import com.example.pos_demo_mvvm.data.model.auth.login.LoginRequest
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.model.auth.register.RegisterRequest
import com.example.pos_demo_mvvm.data.model.barang.BarangResponses
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {
    @POST("/api/login")
    suspend fun login(@Body() payload: LoginRequest): Response<LoginResponses>

    @POST("/api/register")
    suspend fun register(@Body() payload: RegisterRequest): Response<LoginResponses>

    @GET("/api/barang")
    suspend fun getAllBarang() : Response<BarangResponses>
}