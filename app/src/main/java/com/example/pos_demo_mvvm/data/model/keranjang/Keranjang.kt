package com.example.pos_demo_mvvm.data.model.keranjang

import com.example.pos_demo_mvvm.data.model.barang.Barang

data class Keranjang(
    val created_at: String,
    val id: Int,
    var pcs: String,
    val produk_id: String,
    val updated_at: String,
    val barang: Barang
) {
    data class Barangs(
        var barang_id: Int?,
        var pcs: Int,
        val keranjang_id: Int
    )
}
