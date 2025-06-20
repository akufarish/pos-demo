package com.example.pos_demo_mvvm.data.server

import com.example.pos_demo_mvvm.data.model.auth.login.LoginRequest
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.model.auth.register.RegisterRequest
import com.example.pos_demo_mvvm.data.model.barang.BarangResponses
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangRequest
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangResponses
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEndPoint {
    @POST("/api/login")
    suspend fun login(@Body() payload: LoginRequest): Response<LoginResponses>

    @POST("/api/register")
    suspend fun register(@Body() payload: RegisterRequest): Response<LoginResponses>

    @GET("/api/barang")
    suspend fun getAllBarang(): Response<BarangResponses>

    @POST("/api/logout")
    suspend fun doLogout(): Response<LoginResponses>

    @POST("/api/keranjang/{id}")
   suspend fun StoreKeranjang(
        @Path("id") id: Int,
        @Body payload: KeranjangRequest
    ): Response<KeranjangResponses>

    @GET("/api/keranjang")
    suspend fun indexKeranjang(): Response<KeranjangResponses>

    @GET("/api/transaksi")
    suspend fun indexRiwayatTransaksi(): Response<TransaksiResponses>
}