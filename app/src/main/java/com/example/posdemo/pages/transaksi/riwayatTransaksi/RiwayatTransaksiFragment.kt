package com.example.posdemo.pages.transaksi.riwayatTransaksi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.posdemo.databinding.FragmentRiwayatTransaksiBinding
import com.example.posdemo.pages.transaksi.TransaksiActivity
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.services.paging.RiwayatTransaksiAdapter
import com.example.posdemo.services.transaksi.TransaksiServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RiwayatTransaksiFragment : Fragment() {

    private val viewModel: RiwayatTransaksiViewModel by viewModels()

    private var _binding: FragmentRiwayatTransaksiBinding? = null
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
        _binding = FragmentRiwayatTransaksiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

//        val riwayatTransaksiAdapter = RiwayatTransaksiAdapter(arrayListOf(), requireContext(),
//            object : RiwayatTransaksiAdapter.OnAdapterListener {
//                override fun onClick(data: RiwayatTransaksiResponses.Transaksi) {
//                    Log.d("riwayat_transaksi", data.id.toString())

//                }
//
//            })

//        TransaksiServices.getTransaksi(adapter = riwayatTransaksiAdapter)
        val riwayatTransaksiAdapter = RiwayatTransaksiAdapter(requireContext(), object : RiwayatTransaksiAdapter.OnAdapterListener{
            override fun onClick(data: RiwayatTransaksiResponses.Transaksi) {
                startActivity(
                    Intent(requireContext(), TransaksiActivity::class.java)
                        .putExtra("id", data.id.toString())
                )
            }
        })

        viewModel.list.observe(viewLifecycleOwner, Observer {
            riwayatTransaksiAdapter.submitData(lifecycle, it)
        })

        binding.riwayatTransaksiRecyclerView.apply {
            adapter = riwayatTransaksiAdapter
        }
    }
}