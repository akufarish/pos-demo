package com.example.pos_demo_mvvm.data.model.transaksi

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
