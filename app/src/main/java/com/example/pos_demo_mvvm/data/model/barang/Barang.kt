package com.example.pos_demo_mvvm.data.model.barang

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Barang(
    val id: Int,
    val nama_produk: String,
    val harga_produk: String,
    val image: String,
    val pcs: String
) : Parcelable
