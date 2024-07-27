package com.example.posdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posdemo.databinding.ActivityTransaksiBinding
import com.example.posdemo.databinding.TransaksiItemBinding
import com.example.posdemo.responses.TransaksiResponses
import java.text.NumberFormat
import java.util.Locale

class TransaksiAdapter(
    private val dataTransaksi: ArrayList<TransaksiResponses>,
    private val context: Context,
    private val binding: ActivityTransaksiBinding
) : RecyclerView.Adapter<TransaksiAdapter.TransaksiViewHolder>() {
    class TransaksiViewHolder(var binding: TransaksiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiViewHolder {
        val binding = TransaksiItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return TransaksiViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataTransaksi.size
    }

    override fun onBindViewHolder(holder: TransaksiViewHolder, position: Int) {
        val currentItem = dataTransaksi[position]
        binding.kodeTransaksi.text = currentItem.transaksi.transaksi_code
        holder.binding.totalHarga.text = formatCurrency(currentItem.transaksi.total_harga)
        holder.binding.kembalian.text = formatCurrency(currentItem.transaksi.kembalian)
        holder.binding.bayaran.text = formatCurrency(currentItem.transaksi.bayaran)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDataTransaksi(data: TransaksiResponses) {
        dataTransaksi.clear()
        dataTransaksi.add(data)
        Log.d("data_transaksi", data.toString())
        notifyDataSetChanged()
    }

    private fun formatCurrency(nominal: Int): String {

        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(nominal)
    }
}