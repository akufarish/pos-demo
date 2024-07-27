package com.example.posdemo.requests

data class TransaksiRequest(
    val total_harga: Int,
    val barang: List<Barangs>
) {
    data class Barangs(
        val barang_id: Int,
        val pcs: Int,
        val keranjang_id: Int
    )
}
