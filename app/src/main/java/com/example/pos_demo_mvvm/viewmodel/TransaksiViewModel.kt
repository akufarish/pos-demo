package com.example.pos_demo_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import com.example.pos_demo_mvvm.data.repository.TransaksiRepository
import com.example.pos_demo_mvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransaksiViewModel @Inject constructor(private val repository: TransaksiRepository): ViewModel() {
    var dataTransaksi = MutableLiveData<MyResponse<TransaksiResponses>>()

    fun getAllTransaksi() = viewModelScope.launch {
        repository.indexTransaksi().collect({
            dataTransaksi.postValue(it)
        })
    }
}