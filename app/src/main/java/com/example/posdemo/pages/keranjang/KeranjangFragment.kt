package com.example.posdemo.pages.keranjang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.posdemo.adapter.KeranjangAdapter
import com.example.posdemo.adapter.TransaksiAdapter
import com.example.posdemo.databinding.FragmentKeranjangBinding
import com.example.posdemo.models.Transaksi
import com.example.posdemo.pages.transaksi.TransaksiActivity
import com.example.posdemo.services.keranjang.KeranjangService
import com.example.posdemo.services.transaksi.TransaksiServices

class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentKeranjangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val keranjangAdapter =
            KeranjangAdapter(
                arrayListOf(),
                requireContext(),
                binding,
                object : KeranjangAdapter.OnAdepterListener {
                    override fun onClick(result: Transaksi) {
                        Log.d("keranjang_api", result.toString())

                        val transaksiPayload =
                            Transaksi(result.total_harga, result.barang, bayaran = binding.bayaranEditText.text.toString().toInt())

                        TransaksiServices.StoreBarang(transaksiPayload)

                        startActivity(
                            Intent(requireContext(), TransaksiActivity::class.java)
                        )
                    }
                })

        KeranjangService.IndexdKeranjang(keranjangAdapter)

        binding.apply {
            binding.keranjangRecyclerView.apply {
                adapter = keranjangAdapter
            }
        }
    }
}