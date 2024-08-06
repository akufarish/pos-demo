package com.example.posdemo.pages.transaksi.riwayatTransaksi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.posdemo.repository.RiwayatTransaksiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RiwayatTransaksiViewModel @Inject constructor(
    private val riwayatTransaksiRepository: RiwayatTransaksiRepository
) : ViewModel() {
    val list = riwayatTransaksiRepository.getTransaksi().cachedIn(viewModelScope)
}
