package com.example.posdemo.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.posdemo.databinding.ActivityTransaksiBinding
import com.example.posdemo.databinding.TransaksiItemBinding
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.services.Common
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DetailRiwiayatTransaksiAdapter(
    private val dataRiwayatTransaksi: ArrayList<RiwayatTransaksiResponses.Transaksi>,
    private val context: Context,
    private val binding: ActivityTransaksiBinding,
): RecyclerView.Adapter<DetailRiwiayatTransaksiAdapter.ViewHolder>() {
    class ViewHolder(val binding: TransaksiItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TransaksiItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataRiwayatTransaksi.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataRiwayatTransaksi[position]
        holder.binding.totalHarga.text = Common.formatCurrency(currentItem.total_harga)
        holder.binding.bayaran.text = Common.formatCurrency(currentItem.bayaran)
        holder.binding.kembalian.text = Common.formatCurrency(currentItem.kembalian)

        binding.kodeTransaksi.text = currentItem.transaksi_code

        val zonedDateTime = ZonedDateTime.parse(currentItem.created_at)
        val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDateTime = localDateTime.format(formatter)

        holder.binding.date.text = formattedDateTime
        val adapterr = DetailRiwiayatTransaksiBarangAdapter(currentItem.barang, context)

        holder.binding.barangRecyclerView.apply {
            adapter = adapterr
        }

    }

    fun setSingleData(data: RiwayatTransaksiResponses.Transaksi) {
        dataRiwayatTransaksi.clear()
        dataRiwayatTransaksi.add(data)
        notifyDataSetChanged()
    }
}