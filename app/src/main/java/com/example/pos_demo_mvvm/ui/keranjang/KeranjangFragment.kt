package com.example.pos_demo_mvvm.ui.keranjang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.databinding.FragmentKeranjangBinding
import com.example.pos_demo_mvvm.ui.adapter.KeranjangAdapter
import com.example.pos_demo_mvvm.utils.MyResponse
import com.example.pos_demo_mvvm.utils.MyResponse.Status.*
import com.example.pos_demo_mvvm.viewmodel.KeranjangViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null

    private val keranjangViewModel: KeranjangViewModel by viewModels()

    @Inject
     lateinit var keranjangAdapter: KeranjangAdapter
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
        _binding = FragmentKeranjangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupViews()
        getData()
        keranjangViewModel.indexKeranjang()
    }

    private fun getData() {
        keranjangViewModel.dataKeranjang.observe(this@KeranjangFragment){responses ->
            run {
                when(responses.status) {
                    LOADING -> {}
                    SUCCESS -> {
                        responses?.data?.data.let { keranjangAdapter.setData(it!!) }
                    }
                    ERROR -> {}
                }
            }
        }
    }

    private fun setupViews() {
        keranjangAdapter.fragmentKeranjangBinding = binding
        binding.keranjangRecyclerView.apply {
            adapter = keranjangAdapter
        }
    }

}