package com.example.posdemo.responses

data class Keranjang(
    val created_at: String,
    val id: Int,
    var pcs: String,
    val produk_id: String,
    val updated_at: String,
    val barang: Barang
)