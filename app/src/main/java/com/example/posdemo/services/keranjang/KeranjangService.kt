package com.example.posdemo.services.keranjang

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.posdemo.adapter.KeranjangAdapter
import com.example.posdemo.requests.KeranjangRequests
import com.example.posdemo.responses.KeranjangResponses
import com.example.posdemo.retrofit.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object KeranjangService {
    fun StoreKeranjang(id:Int, payload: KeranjangRequests, context: Context) {
        ApiServices.endPoint.StoreKeranjang(id, payload).enqueue(object: Callback<KeranjangResponses>{
            override fun onResponse(
                p0: Call<KeranjangResponses>,
                p1: Response<KeranjangResponses>
            ) {
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    Toast.makeText(context, "Barang berhasil ditambah ke keranjang", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<KeranjangResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun IndexdKeranjang(adapter: KeranjangAdapter) {
        ApiServices.endPoint.IndexKeranjang().enqueue(object: Callback<KeranjangResponses>{
            override fun onResponse(
                p0: Call<KeranjangResponses>,
                p1: Response<KeranjangResponses>
            ) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    if (result != null) {
                    adapter.addKeranjang(result.data)
                    }
                }
            }

            override fun onFailure(p0: Call<KeranjangResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun printLog(msg: String) {
        Log.d("keranjang", msg)
    }
}