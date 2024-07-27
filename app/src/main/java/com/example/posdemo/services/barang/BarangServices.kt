package com.example.posdemo.services.barang

import android.util.Log
import com.example.posdemo.adapter.BarangAdapter
import com.example.posdemo.adapter.DetailBarangAdapter
import com.example.posdemo.responses.BarangResponses
import com.example.posdemo.retrofit.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BarangServices {
    fun IndexBarang(adapter: BarangAdapter) {
        ApiServices.endPoint.IndexBarang().enqueue(object : Callback<BarangResponses> {
            override fun onResponse(p0: Call<BarangResponses>, p1: Response<BarangResponses>) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())

                    if (result != null) {
                        adapter.setAllBarang(result.data)
                    }

                }
            }

            override fun onFailure(p0: Call<BarangResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun ShowBarang(id: Int, adapter: DetailBarangAdapter) {
        ApiServices.endPoint.ShowBarang(id).enqueue(object: Callback<BarangResponses>{
            override fun onResponse(p0: Call<BarangResponses>, p1: Response<BarangResponses>) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    if (result != null) {
                        adapter.addBarang(result.barang)
                    }
                }
            }

            override fun onFailure(p0: Call<BarangResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }


    fun printLog(msg: String) {
        Log.d("hit_api", msg)
    }
}