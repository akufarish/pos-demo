package com.example.pos_demo_mvvm.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import com.example.pos_demo_mvvm.databinding.RiwayatTransaksiItemBinding
import com.example.pos_demo_mvvm.utils.dateFormat
import com.example.pos_demo_mvvm.utils.formatCurrency
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)

class RiwayatTransaksiAdapter @Inject constructor(
    @ActivityContext private val context: Context
): RecyclerView.Adapter<RiwayatTransaksiAdapter.ViewHolder>() {

    private var transaksiData = emptyList<TransaksiResponses.Transaksi>()

    var onClickListener: ((TransaksiResponses.Transaksi) -> Unit)? = null
   inner class ViewHolder(private var binding: RiwayatTransaksiItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TransaksiResponses.Transaksi) {
            binding.kodeTransaksi.text = data.transaksi_code
            binding.waktuTransaksi.text = dateFormat(data.created_at)
            binding.totalHarga.text = formatCurrency(data.total_harga)

            itemView.setOnClickListener {
                onClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RiwayatTransaksiItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transaksiData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(transaksiData[position])
    }

    fun setData(data: List<TransaksiResponses.Transaksi>) {
        val newDiffUtil = NewDiffUtils(transaksiData, data)
        val diffUtils = DiffUtil.calculateDiff(newDiffUtil)
        transaksiData = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NewDiffUtils(private val oldItem: List<TransaksiResponses.Transaksi>,private val newItem: List<TransaksiResponses.Transaksi>): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] == newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] == newItem[newItemPosition]
        }

    }
}