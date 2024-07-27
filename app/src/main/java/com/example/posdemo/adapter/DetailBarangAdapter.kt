package com.example.posdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.posdemo.databinding.DetailBarangItemBinding
import com.example.posdemo.models.Barang
import com.example.posdemo.requests.KeranjangRequests
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.services.Common
import com.example.posdemo.services.keranjang.KeranjangService

class DetailBarangAdapter(
    private var dataBarang: ArrayList<Barang>,
    private var context: Context,
) : RecyclerView.Adapter<DetailBarangAdapter.DetailBarangViewHolder>() {
    class DetailBarangViewHolder(val binding: DetailBarangItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBarangViewHolder {
        val binding = DetailBarangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailBarangViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataBarang.size
    }

    override fun onBindViewHolder(holder: DetailBarangViewHolder, position: Int) {
        val currentItem = dataBarang[position]
        holder.binding.detailBarangTitle.text = currentItem.nama_produk
        Glide.with(holder.itemView)
            .load(ApiServices.BASE_URL + currentItem.image)
            .into(holder.binding.detailBarangImage)

        var pcsValue = 0
        holder.binding.plus.setOnClickListener {
            pcsValue++
            val totalHarga = currentItem.harga_produk.toInt() * pcsValue
            Log.d("angka", pcsValue.toString())
            holder.binding.pcs.text = pcsValue.toString()

            holder.binding.totalHarga.text = Common.formatCurrency(totalHarga)
        }

        holder.binding.minus.setOnClickListener {
            if (pcsValue > 1) {
                pcsValue--
                val totalHarga = currentItem.harga_produk.toInt() * pcsValue
                holder.binding.pcs.text = pcsValue.toString()
                holder.binding.totalHarga.text =Common.formatCurrency(totalHarga)
            }
        }

        holder.binding.tambahKeranjang.setOnClickListener {
            val payload = KeranjangRequests(
                pcs = pcsValue,
                produk_id = currentItem.id
            )

            Log.d("test_api", payload.toString())
            KeranjangService.StoreKeranjang(currentItem.id, payload, context)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBarang(data: Barang) {
        dataBarang.clear()
        dataBarang.add(data)
        notifyDataSetChanged()
    }
}