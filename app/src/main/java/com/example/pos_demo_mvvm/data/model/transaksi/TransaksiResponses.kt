package com.example.pos_demo_mvvm.data.model.transaksi

import android.os.Parcelable
import com.example.pos_demo_mvvm.data.model.barang.Barang
import kotlinx.parcelize.Parcelize


data class TransaksiResponses(
    val data: List<Transaksi>,
    val currentPage: Int,
    val total: Int,
    val datas: Transaksi
) {
    @Parcelize
    data class Transaksi(
        val id: Int,
        val created_at: String,
        val updated_at: String,
        val total_harga: Int,
        val bayaran: Int,
        val kembalian: Int,
        val barang: ArrayList<Barangs>,
        val transaksi_code: String,
        val user_id: Int,
    ) : Parcelable {
        @Parcelize
        data class Barangs(
            val data_transaksi: Int,
            val transaksi_id: Int,
            val barang_id: Int,
            val keranjang_id: Int,
            val barang: Barang,
            val pcs: Int
        ) : Parcelable
    }
}
