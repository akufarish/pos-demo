package com.example.pos_demo_mvvm.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.data.model.barang.Barang
import com.example.pos_demo_mvvm.databinding.BarangItemBinding
import com.example.pos_demo_mvvm.ui.listeners.BarangListeners
import com.example.pos_demo_mvvm.utils.BASE_URL
import com.example.pos_demo_mvvm.utils.formatCurrency
import com.example.pos_demo_mvvm.viewmodel.BarangViewModel
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class BarangAdapter @Inject constructor(
    @ActivityContext private val context: Context,
) :
    RecyclerView.Adapter<BarangAdapter.ViewHolder>() {


    private lateinit var binding: BarangItemBinding
    private var barang = emptyList<Barang>()

    var onClickListeners: ((Barang) -> Unit)? = null

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Barang) {
            binding.apply {
                binding.foodTitle.text = data.nama_produk
                binding.hargaBarang.text = formatCurrency(data.harga_produk.toInt())

                if (data.image == null) {
                    binding.foodImage.setImageResource(R.drawable.ic_image)
                } else Glide.with(itemView)
                    .load(BASE_URL + data.image)
                    .into(binding.foodImage)
            }
            itemView.setOnClickListener {
                onClickListeners?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = BarangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return barang.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(barang[position])
    }

    fun setData(data: List<Barang>) {
        val newDiffUtil = NewDiffUtils(barang, data)
        val diffUtils = DiffUtil.calculateDiff(newDiffUtil)
        barang = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NewDiffUtils(
        private val oldItem: List<Barang>,
        private val newItem: List<Barang>
    ) : DiffUtil.Callback() {
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