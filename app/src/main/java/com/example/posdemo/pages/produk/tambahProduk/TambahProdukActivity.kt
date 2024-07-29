package com.example.posdemo.pages.produk.tambahProduk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.posdemo.R
import com.example.posdemo.databinding.ActivityTambahProdukBinding
import com.example.posdemo.requests.BarangRequest
import com.example.posdemo.services.barang.BarangServices

class TambahProdukActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahProdukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tambahButton.setOnClickListener {
            val namaEditText = binding.namaBarangEditText.text.toString()
            val hargaProdukEditText = binding.hargaEditText.text.toString()

            val payload = BarangRequest(
                nama_produk = namaEditText,
                harga_produk = hargaProdukEditText
            )

            BarangServices.storeBarang(payload, applicationContext)

            Toast.makeText(applicationContext, "Data berhasil ditambah!", Toast.LENGTH_SHORT).show()
        }
    }
}