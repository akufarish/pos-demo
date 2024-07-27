package com.example.posdemo.models

import com.example.posdemo.responses.Barang

data class Transaksi(
    val total_harga: Int,
    val barang: List<Barangs>,
    val bayaran: Int?
) {
    data class Barangs(
        var barang_id: Int?,
        var pcs: Int,
        val keranjang_id: Int
    )
}
