package com.example.posdemo.pages.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posdemo.adapter.TransaksiAdapter
import com.example.posdemo.databinding.ActivityTransaksiBinding
import com.example.posdemo.services.transaksi.TransaksiServices

class TransaksiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransaksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaksiAdapter = TransaksiAdapter(arrayListOf(), applicationContext, binding)

        TransaksiServices.latestTransaksi(transaksiAdapter)

        binding.transaksiRecyclerView.apply {
            adapter = transaksiAdapter
        }
    }
}