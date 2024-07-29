package com.example.posdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.posdemo.R
import com.example.posdemo.databinding.BarangItemBinding
import com.example.posdemo.models.Barang
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.services.Common

class BarangAdapter(
    private var dataBarang: ArrayList<Barang>,
    private var context: Context,
    private val listener: OnAdepterListener

) : RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {
    class BarangViewHolder(val binding: BarangItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = BarangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BarangViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataBarang.size
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val currentData = dataBarang[position]
        holder.binding.foodLocation.text = Common.formatCurrency(currentData.harga_produk.toInt())
        holder.binding.foodTitle.text = currentData.nama_produk

        if (currentData.image == null) {
            holder.binding.foodImage.setImageResource(R.drawable.ic_image)
        } else {
        Glide.with(holder.itemView)
            .load(ApiServices.BASE_URL + currentData.image)
            .into(holder.binding.foodImage)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(currentData)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllBarang(data: List<Barang>) {
        dataBarang.clear()
        dataBarang.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdepterListener {
        fun onClick(result: Barang)
    }

}