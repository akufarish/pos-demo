package com.example.pos_demo_mvvm.ui.produk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.data.model.barang.Barang
import com.example.pos_demo_mvvm.ui.adapter.BarangAdapter
import com.example.pos_demo_mvvm.databinding.FragmentProdukBinding
import com.example.pos_demo_mvvm.utils.MyResponse
import com.example.pos_demo_mvvm.utils.MyResponse.Status.*
import com.example.pos_demo_mvvm.viewmodel.BarangViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProdukFragment : Fragment() {

    private var _binding: FragmentProdukBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BarangViewModel by viewModels()

    @Inject
    lateinit var barangAdapter: BarangAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdukBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupViews()
        getData()
        viewModel.getAllBarang()
    }

    private fun setupViews() {
        barangAdapter.onClickListeners = {barang ->
            run {
                Log.d("barang_api", barang.nama_produk)
            }
        }
        binding.barangRecyclerView.apply {
            adapter = barangAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun getData() {
        viewModel.dataBarang.observe(this@ProdukFragment){responses ->
            run {
                when(responses.status){
                    LOADING -> {}
                    SUCCESS -> {
                        responses?.data?.data.let { barangAdapter.setData(it!!) }
                    }
                    ERROR -> {

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}