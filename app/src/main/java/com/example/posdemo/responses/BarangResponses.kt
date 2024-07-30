package com.example.posdemo.responses

import com.example.posdemo.models.Barang

data class BarangResponses(
    val `data`: List<Barang>,
    val barang: Barang,
    val barangs: CreatedBarangResponses
) {
    data class CreatedBarangResponses(
        val nama_produk: String,
        val harga_produk: String,
        val pcs: String
    )
}