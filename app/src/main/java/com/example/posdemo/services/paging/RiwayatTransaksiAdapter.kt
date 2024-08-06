package com.example.posdemo.services.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.posdemo.databinding.RiwayatTransaksiItemBinding
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.services.Common
import java.security.PrivateKey

class RiwayatTransaksiAdapter(
    private val context: Context,
    private val listener: OnAdapterListener
): PagingDataAdapter<RiwayatTransaksiResponses.Transaksi, RiwayatTransaksiAdapter.ViewHolder>(
    COMPARATOR)
{
    class ViewHolder(val binding: RiwayatTransaksiItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.kodeTransaksi.text = item?.transaksi_code
        holder.binding.totalHarga.text = Common.formatCurrency(item?.total_harga!!)

        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RiwayatTransaksiItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RiwayatTransaksiResponses.Transaksi>() {
            override fun areItemsTheSame(oldItem: RiwayatTransaksiResponses.Transaksi, newItem: RiwayatTransaksiResponses.Transaksi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RiwayatTransaksiResponses.Transaksi, newItem: RiwayatTransaksiResponses.Transaksi): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnAdapterListener {
        fun onClick(data: RiwayatTransaksiResponses.Transaksi)
    }
}