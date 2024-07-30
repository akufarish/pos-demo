package com.example.posdemo.services.barang

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.posdemo.adapter.BarangAdapter
import com.example.posdemo.adapter.DetailBarangAdapter
import com.example.posdemo.pages.MainActivity
import com.example.posdemo.requests.BarangRequest
import com.example.posdemo.responses.BarangResponses
import com.example.posdemo.retrofit.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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
        ApiServices.endPoint.ShowBarang(id).enqueue(object : Callback<BarangResponses> {
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


    private fun printLog(msg: String) {
        Log.d("barang_api", msg)
    }

//    fun storeBarang(payload: BarangRequest) {
//        ApiServices.endPoint.createBarang(
//            namaProduk = payload.nama_produk,
//            hargaProduk = payload.harga_produk,
//            pcs = payload.pcs,
//            image = payload.image
//        ).enqueue(object : Callback<BarangResponses> {
//            override fun onResponse(p0: Call<BarangResponses>, p1: Response<BarangResponses>) {
//                if (p1.isSuccessful) {
//                    printLog(p1.body().toString())
//                }
//            }
//
//            override fun onFailure(p0: Call<BarangResponses>, p1: Throwable) {
//                printLog(p1.toString())
//            }
//
//        })
//    }

    fun storeBarang(payload: BarangRequest, context: Context) {
        printLog(payload.nama_produk.body.toString())
        ApiServices.endPoint.createBarang(
            payload.nama_produk,
            payload.harga_produk,
            payload.pcs,
            payload.image
        ).enqueue(object : Callback<BarangResponses> {
            override fun onResponse(p0: Call<BarangResponses>, p1: Response<BarangResponses>) {
                printLog(p1.isSuccessful.toString())
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    Toast.makeText(context, "Data berhasil ditambah!", Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        context.startActivity(
                            Intent(context, MainActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        )
                    }, 2000)
                }
            }

            override fun onFailure(p0: Call<BarangResponses>, p1: Throwable) {
                printLog(p1.toString())
            }

        })
    }
}