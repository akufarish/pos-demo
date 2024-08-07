package com.example.pos_demo_mvvm.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pos_demo_mvvm.data.model.keranjang.Keranjang
import com.example.pos_demo_mvvm.databinding.FragmentKeranjangBinding
import com.example.pos_demo_mvvm.databinding.KeranjangItemBinding
import com.example.pos_demo_mvvm.utils.BASE_URL
import com.example.pos_demo_mvvm.utils.formatCurrency
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class KeranjangAdapter @Inject constructor(@ActivityContext private val context: Context)
    :RecyclerView.Adapter<KeranjangAdapter.ViewHolder>(){

        private var totalHarga = 0
        private var keranjangData = emptyList<Keranjang>()
        var fragmentKeranjangBinding: FragmentKeranjangBinding? = null

    private val barang = arrayListOf<Keranjang.Barangs>()
    inner class ViewHolder(private var binding: KeranjangItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(data: Keranjang) {
            binding.foodTitle.text = data.barang.nama_produk

            Glide.with(itemView)
                .load(BASE_URL + data.barang.image)
                .into(binding.foodImage)

            var pcsValue = 1
            var newHarga: Int

            binding.plusBtn.setOnClickListener {
                pcsValue++
                newHarga = data.barang.harga_produk.toInt() * pcsValue
                binding.pcs.text = pcsValue.toString()
                data.pcs = pcsValue.toString()
                binding.hargaProduk.text = formatCurrency(newHarga)
                updatePcsBarang(data, pcsValue)
            }

            binding.minBtn.setOnClickListener {
                if (pcsValue > 1) {
                    pcsValue--
                    newHarga = data.barang.harga_produk.toInt() * pcsValue
                    binding.pcs.text = pcsValue.toString()
                    data.pcs = pcsValue.toString()
                    binding.hargaProduk.text = formatCurrency(newHarga)
                    updatePcsBarang(data, pcsValue)
                }
            }

            updatePcsBarang(data, pcsValue)

            data.pcs = pcsValue.toString()

            var totalHarga = 0
            for (datas in keranjangData) {

                totalHarga += datas.barang.harga_produk.toInt() * datas.pcs.toInt()
                datas.pcs = pcsValue.toString()
            }

            fragmentKeranjangBinding?.totalHarga?.visibility = View.VISIBLE
            fragmentKeranjangBinding?.totalHarga?.text = formatCurrency(totalHarga)
            binding.hargaProduk.text =
                formatCurrency(data.barang.harga_produk.toInt() * data.pcs.toInt())
            binding.foodPcs.text = "${data.pcs} pcs"
        }
    }

    private fun updatePcsBarang(data: Keranjang, pcsValue: Int) {
        for (i in keranjangData) {
            if (i.barang.id == data.barang.id) {
                i.pcs = pcsValue.toString()
                break
            }
        }

        updateTotalHarga()
    }

    private fun updateTotalHarga() {
        barang.clear()
        totalHarga = 0

        for (datas in keranjangData) {
            totalHarga += datas.barang.harga_produk.toInt() * datas.pcs.toInt()
            barang.add(
                Keranjang.Barangs(
                    keranjang_id = datas.id,
                    pcs = datas.pcs.toInt(),
                    barang_id = datas.barang.id
                )
            )
        }

        fragmentKeranjangBinding?.totalHarga?.text = formatCurrency(totalHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = KeranjangItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return keranjangData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(keranjangData[position])
    }

    fun setData(data: List<Keranjang>) {
        val newDiffUtil = NewDiffUtils(keranjangData, data)
        val diffUtils = DiffUtil.calculateDiff(newDiffUtil)
        keranjangData =  data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NewDiffUtils(private val oldItem: List<Keranjang>, private val newItem: List<Keranjang>)
        :DiffUtil.Callback(){
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