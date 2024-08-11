package com.example.pos_demo_mvvm.ui.transaksi.detailTransaksi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import com.example.pos_demo_mvvm.databinding.ActivityDetailTransaksiBinding
import com.example.pos_demo_mvvm.ui.adapter.BarangTransaksiAdapter
import com.example.pos_demo_mvvm.utils.dateFormat
import com.example.pos_demo_mvvm.utils.formatCurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTransaksiActivity : AppCompatActivity() {


    private var _binding: ActivityDetailTransaksiBinding?  = null
    private val binding get() = _binding

    private var kodeTransaksi: String? = null
    private var date: String? = null
    private var totalHarga: String? = null
    private var kembalian: String? = null
    private var bayaran: String? = null
    private var barang: ArrayList<TransaksiResponses.Transaksi.Barangs>? = null
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTransaksiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        kodeTransaksi = intent.getStringExtra("transaksiCode")
        date = intent.getStringExtra("date")
        totalHarga = intent.getIntExtra("totalHarga", 0).toString()
        kembalian = intent.getIntExtra("kembalian", 0).toString()
        barang = intent.getParcelableArrayListExtra("barang")
        bayaran = intent.getIntExtra("bayaran", 0).toString()

        printLog(kodeTransaksi)
        printLog(date)
        printLog(totalHarga)
        printLog(kembalian)
        printLog(barang.toString())

        binding?.kodeTransaksi?.text = kodeTransaksi
        binding?.date?.text = dateFormat(date!!)
        binding?.totalHarga?.text = formatCurrency(totalHarga!!.toInt())
        binding?.bayaran?.text = formatCurrency(bayaran!!.toInt())
        binding?.kembalian?.text = formatCurrency(kembalian!!.toInt())

        val barangAdapter = BarangTransaksiAdapter(applicationContext)
        barangAdapter.setData(barang!!)

        binding?.barangRecyclerView?.apply {
            adapter = barangAdapter
        }

    }

    private fun printLog(data: String?) {
        if (data != null) {
            Log.d("detail_transaksi", data)
        } else {
            Log.d("detail_transaksi", "kosong")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}