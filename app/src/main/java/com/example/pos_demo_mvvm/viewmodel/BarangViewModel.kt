package com.example.pos_demo_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.barang.BarangResponses
import com.example.pos_demo_mvvm.data.repository.BarangRepository
import com.example.pos_demo_mvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarangViewModel @Inject constructor(private val repository: BarangRepository): ViewModel() {
    var dataBarang = MutableLiveData<MyResponse<BarangResponses>>()
    fun getAllBarang() = viewModelScope.launch {
        repository.indexBarang().collect({
            dataBarang.postValue(it)
        })
    }

    fun showBarang(id: Int) = viewModelScope.launch {  }
}