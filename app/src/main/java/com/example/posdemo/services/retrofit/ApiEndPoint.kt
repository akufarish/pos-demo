package com.example.posdemo.retrofit

import com.example.posdemo.models.Transaksi
import com.example.posdemo.requests.KeranjangRequests
import com.example.posdemo.requests.LoginRequest
import com.example.posdemo.requests.RegisterRequest
import com.example.posdemo.responses.BarangResponses
import com.example.posdemo.responses.KeranjangResponses
import com.example.posdemo.responses.LoginResponses
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.responses.TransaksiResponses
import com.example.posdemo.responses.UserRespones
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEndPoint {
    @GET("/api/barang")
    fun IndexBarang(): Call<BarangResponses>

    @GET("/api/barang/{id}")
    fun ShowBarang(@Path("id") id: Int): Call<BarangResponses>

    @POST("/api/keranjang/{id}")
    fun StoreKeranjang(@Path("id") id: Int, @Body payload: KeranjangRequests): Call<KeranjangResponses>

    @GET("/api/keranjang")
    fun IndexKeranjang(): Call<KeranjangResponses>

    @POST("/api/transaksi")
    fun StoreTransaksi(@Body() payload: Transaksi): Call<TransaksiResponses>

    @POST("/api/login")
    fun login(@Body() payload: LoginRequest): Call<LoginResponses>

    @POST("/api/register")
    fun register(@Body() payload: RegisterRequest): Call<LoginResponses>

    @GET("/api/transaksi-latest")
    fun latestTransaksi(): Call<TransaksiResponses>

    @GET("/api/user")
    fun getAuthProfile(): Call<UserRespones>

    @GET("/api/transaksi")
    fun getAllTransaksi(): Call<RiwayatTransaksiResponses>

    @GET("/api/transaksi/{id}")
    fun getDetailTransaksi(@Path("id") id: Int): Call<RiwayatTransaksiResponses>
}