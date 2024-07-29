package com.example.posdemo.pages.produk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.posdemo.adapter.BarangAdapter
import com.example.posdemo.adapter.DetailBarangAdapter
import com.example.posdemo.databinding.BottomSheetBinding
import com.example.posdemo.databinding.FragmentProdukBinding
import com.example.posdemo.models.Barang
import com.example.posdemo.services.auth.AuthServices
import com.example.posdemo.services.barang.BarangServices
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null
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
        _binding = FragmentProdukBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        AuthServices.getAuth(binding, requireContext())

        val barangAdapter = BarangAdapter(
            arrayListOf(),
            requireContext(),
            object : BarangAdapter.OnAdepterListener {
                override fun onClick(result: Barang) {
                    openBottomSheet(result.id)
                }
            })

        BarangServices.IndexBarang(barangAdapter)

        binding.apply {
            binding.barangRecyclerView.apply {
                adapter = barangAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    fun openBottomSheet(id: Int) {
        val sheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)

        val detailBarangAdapter = DetailBarangAdapter(arrayListOf(), requireContext())

        BarangServices.ShowBarang(id, detailBarangAdapter)

        bottomSheetBinding.detailBarangRecyclerView.apply {
            adapter = detailBarangAdapter
        }

        sheetDialog.apply {
            setContentView(bottomSheetBinding.root)
            show()
        }
    }
}