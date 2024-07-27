package com.example.posdemo.responses

import com.example.posdemo.models.Barang

data class BarangResponses(
    val `data`: List<Barang>,
    val barang: Barang
)