package com.example.pos_demo_mvvm.data.model.barang

import com.google.gson.annotations.SerializedName

data class Barang(
    val id: Int,
    val nama_produk: String,
    val harga_produk: String,
    @SerializedName("urlToImage")
    val image: String,
    val pcs: String
)
