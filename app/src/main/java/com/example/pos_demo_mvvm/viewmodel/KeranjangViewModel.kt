package com.example.pos_demo_mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangRequest
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangResponses
import com.example.pos_demo_mvvm.data.repository.KeranjangRepository
import com.example.pos_demo_mvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeranjangViewModel @Inject constructor(private val repository: KeranjangRepository) :
    ViewModel() {
    var dataKeranjang = MutableLiveData<MyResponse<KeranjangResponses>>()

    fun storeKeranjang(id: Int, payload: KeranjangRequest) {
        dataKeranjang.value = MyResponse.loading()
        viewModelScope.launch {
            try {
                val payloads = KeranjangRequest(
                    pcs = payload.pcs,
                    produk_id = payload.produk_id
                )
                val responses = repository.storeKeranjang(id, payloads)

                val body = responses.body()

                Log.d("keranjang_api", responses.code().toString())
                if (responses.isSuccessful) {
                    Log.d("keranjang_api", body.toString())
                }
            } catch (e: Exception) {
                Log.d("keranjang_api", e.toString())
            }
        }
    }

    fun indexKeranjang() = viewModelScope.launch {
        repository.indexKeranjang().collect({
            dataKeranjang.postValue(it)
        })
    }
}