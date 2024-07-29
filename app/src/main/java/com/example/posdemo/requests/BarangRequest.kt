package com.example.posdemo.requests

import okhttp3.MultipartBody

data class BarangRequest(
    val nama_produk: String,
    val harga_produk: String,
//    val pcs: Int,
//    val image: MultipartBody.Part
)
