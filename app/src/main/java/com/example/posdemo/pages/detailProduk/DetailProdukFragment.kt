package com.example.posdemo.pages.detailProduk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.posdemo.adapter.DetailBarangAdapter
import com.example.posdemo.databinding.FragmentDetailProdukBinding
import com.example.posdemo.retrofit.ApiServices

class DetailProdukFragment : Fragment() {

    private var _binding: FragmentDetailProdukBinding? = null
    private val binding get() = _binding!!
    private var wisataId: Int? = null

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
        _binding = FragmentDetailProdukBinding.inflate(layoutInflater, container, true)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        wisataId = arguments?.getInt("id")

        val detailBarangAdapter = DetailBarangAdapter(arrayListOf(), requireContext())

        ApiServices.endPoint.ShowBarang(wisataId!!)

        binding.detailBarangRecyclerView.apply {
            adapter = detailBarangAdapter
        }
    }
}