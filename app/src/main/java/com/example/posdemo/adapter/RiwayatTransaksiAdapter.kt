package com.example.posdemo.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.posdemo.databinding.RiwayatTransaksiItemBinding
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.services.Common
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class RiwayatTransaksiAdapter(
   private val dataRiwayatTransaksi: ArrayList<RiwayatTransaksiResponses.Transaksi>,
    private val context: Context,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<RiwayatTransaksiAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RiwayatTransaksiItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RiwayatTransaksiItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  dataRiwayatTransaksi.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataRiwayatTransaksi[position]

        val zonedDateTime = ZonedDateTime.parse(currentItem.created_at)
        val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDateTime = localDateTime.format(formatter)

        holder.binding.kodeTransaksi.text = currentItem.transaksi_code
        holder.binding.waktuTransaksi.text = formattedDateTime
        Log.d("waktu", currentItem.created_at)
        holder.binding.totalHarga.text = Common.formatCurrency(currentItem.total_harga)

        holder.itemView.setOnClickListener {
            listener.onClick(currentItem)
        }
    }

    fun setData(data: List<RiwayatTransaksiResponses.Transaksi>) {
        dataRiwayatTransaksi.clear()
        dataRiwayatTransaksi.addAll(data)
        notifyDataSetChanged()
    }

    fun setSingleData(data: RiwayatTransaksiResponses.Transaksi) {
        dataRiwayatTransaksi.clear()
        dataRiwayatTransaksi.add(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(data: RiwayatTransaksiResponses.Transaksi)
    }
}