package com.example.posdemo.requests

import okhttp3.MultipartBody

data class BarangRequest(
    val nama_produk: MultipartBody.Part,
    val harga_produk: MultipartBody.Part,
    val pcs: MultipartBody.Part,
    val image: MultipartBody.Part
)
