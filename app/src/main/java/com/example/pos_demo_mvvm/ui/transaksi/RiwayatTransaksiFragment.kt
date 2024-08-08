package com.example.pos_demo_mvvm.ui.transaksi

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.databinding.FragmentRiwayatTransaksiBinding
import com.example.pos_demo_mvvm.ui.adapter.RiwayatTransaksiAdapter
import com.example.pos_demo_mvvm.utils.MyResponse
import com.example.pos_demo_mvvm.utils.MyResponse.Status.*
import com.example.pos_demo_mvvm.viewmodel.TransaksiViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class RiwayatTransaksiFragment : Fragment() {

    private var _binding: FragmentRiwayatTransaksiBinding? = null
    private val binding get() = _binding!!

    private val transaksiViewModel: TransaksiViewModel by viewModels()

    @Inject
    lateinit var riwayatTransaksiAdapter: RiwayatTransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatTransaksiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupViews()
        getData()
        transaksiViewModel.getAllTransaksi()
    }

    private fun getData() {
        transaksiViewModel.dataTransaksi.observe(this@RiwayatTransaksiFragment){response ->
            run {
                when (response.status) {
                    LOADING -> {}
                    SUCCESS -> {
                        response?.data?.data.let { riwayatTransaksiAdapter.setData(it!!) }
                    }
                    ERROR -> {}
                }
            }
        }
    }

    private fun setupViews() {
        binding.riwayatTransaksiRecyclerView.apply {
            adapter = riwayatTransaksiAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}