package com.example.posdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.posdemo.databinding.FragmentKeranjangBinding
import com.example.posdemo.databinding.KeranjangItemBinding
import com.example.posdemo.models.Transaksi
import com.example.posdemo.responses.Keranjang
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.services.Common

class KeranjangAdapter(
    private var dataKeranjang: ArrayList<Keranjang>,
    private var context: Context,
    private var fragmentKeranjangBinding: FragmentKeranjangBinding,
    private val listener: OnAdepterListener
) : RecyclerView.Adapter<KeranjangAdapter.KeranjangViewHolder>() {
    class KeranjangViewHolder(var binding: KeranjangItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangViewHolder {
        val binding = KeranjangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return KeranjangViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataKeranjang.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: KeranjangViewHolder, position: Int) {
        val currentItem = dataKeranjang[position]
        Glide.with(holder.itemView)
            .load(ApiServices.BASE_URL + currentItem.barang.image)
            .into(holder.binding.foodImage)
        holder.binding.foodTitle.text = currentItem.barang.nama_produk

        var totalHarga = 0
        val barangs = arrayListOf<Transaksi.Barangs>()
        for (datas in dataKeranjang) {
            totalHarga += datas.barang.harga_produk.toInt() * datas.pcs.toInt()
            barangs.add(
                Transaksi.Barangs(
                    barang_id = datas.barang.id, pcs = datas.pcs.toInt(), keranjang_id = datas.id
                )
            )
        }
//        holder.binding.totalHarga.text = totalHarga.toString()
        fragmentKeranjangBinding.totalHarga.text = Common.formatCurrency(totalHarga)
        holder.binding.hargaProduk.text =
            Common.formatCurrency(currentItem.barang.harga_produk.toInt() * currentItem.pcs.toInt())
        holder.binding.foodPcs.text = "${currentItem.pcs} pcs"

        val transaksi = Transaksi(
            total_harga = totalHarga,
            barang = barangs,
            bayaran = null
        )

        fragmentKeranjangBinding.transaksiButton.setOnClickListener {
            listener.onClick(transaksi)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addKeranjang(data: List<Keranjang>) {
        dataKeranjang.clear()
        dataKeranjang.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdepterListener {
        fun onClick(result: Transaksi)
    }
}