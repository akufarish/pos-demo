package com.example.pos_demo_mvvm.ui.listeners

import com.example.pos_demo_mvvm.data.model.barang.Barang

interface BarangListeners {
    fun OnClick(barang: Barang)
}