package com.example.posdemo.responses

import com.example.posdemo.models.Barang

data class RiwayatTransaksiResponses(
    val transaksi: List<Transaksi>,
    val data: Transaksi
) {
    data class Transaksi(
        val id: Int,
        val created_at: String,
        val updated_at: String,
        val total_harga: Int,
        val bayaran: Int,
        val kembalian: Int,
        val barang: List<Barangs>,
        val transaksi_code: String,
        val user_id: Int,
    ) {
        data class Barangs(
            val data_transaksi: Int,
            val transaksi_id: Int,
            val barang_id: Int,
            val keranjang_id: Int,
            val barang: Barang
        )
    }
}
