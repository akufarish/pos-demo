package com.example.pos_demo_mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import com.example.pos_demo_mvvm.databinding.RiwayatTransaksiBarangItemBinding
import com.example.pos_demo_mvvm.utils.formatCurrency

class BarangTransaksiAdapter(
    private val context: Context,
) : RecyclerView.Adapter<BarangTransaksiAdapter.ViewHolder>() {

    private var dataBarang = emptyList<TransaksiResponses.Transaksi.Barangs>()
    inner class ViewHolder(val binding: RiwayatTransaksiBarangItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TransaksiResponses.Transaksi.Barangs) {
            binding.namaBarang.text = data.barang.nama_produk
            binding.pcs.text = data.barang.pcs.toString()
            binding.total.text = data.pcs.toString()
            binding.hargaSatuan.text = formatCurrency(data.barang.harga_produk.toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RiwayatTransaksiBarangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataBarang.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataBarang[position])
    }

    fun setData(data: List<TransaksiResponses.Transaksi.Barangs>) {
        val newDiffUtils = NewDiffUtils(dataBarang, data)
        val diffUtils = DiffUtil.calculateDiff(newDiffUtils)
        dataBarang = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NewDiffUtils(
        private val oldItem: List<TransaksiResponses.Transaksi.Barangs>,
        private val newItem: List<TransaksiResponses.Transaksi.Barangs>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }
}