package com.example.posdemo.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.posdemo.databinding.TransaksiBarangItemBinding
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.services.Common

class DetailRiwiayatTransaksiBarangAdapter(
    private val dataRiwayatTransaksi: List<RiwayatTransaksiResponses.Transaksi.Barangs>,
    private val context: Context,
): RecyclerView.Adapter<DetailRiwiayatTransaksiBarangAdapter.ViewHolder>() {
    class ViewHolder(val binding: TransaksiBarangItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransaksiBarangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataRiwayatTransaksi.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataRiwayatTransaksi[position]

            holder.binding.namaBarang.text = currentItem.barang.nama_produk
            holder.binding.pcs.text = currentItem.pcs.toString()
            holder.binding.total.text = Common.formatCurrency(currentItem.pcs.toInt() * currentItem.barang.harga_produk.toInt())
            holder.binding.hargaSatuan.text = Common.formatCurrency(currentItem.barang.harga_produk.toInt())

    }
}