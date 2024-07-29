package com.example.posdemo.services.transaksi

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.posdemo.adapter.DetailRiwiayatTransaksiAdapter
import com.example.posdemo.adapter.RiwayatTransaksiAdapter
import com.example.posdemo.adapter.TransaksiAdapter
import com.example.posdemo.models.Transaksi
import com.example.posdemo.pages.transaksi.TransaksiActivity
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.responses.TransaksiResponses
import com.example.posdemo.retrofit.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TransaksiServices {
    fun printLog(msg: String) {
        Log.d("transaksi_api", msg)
    }

    fun StoreBarang(payload: Transaksi, context: Context) {
        ApiServices.endPoint.StoreTransaksi(payload).enqueue(object: Callback<TransaksiResponses>{
            override fun onResponse(p0: Call<TransaksiResponses>, p1: Response<TransaksiResponses>) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    if (result != null) {
                        context.startActivity(
                            Intent(context, TransaksiActivity::class.java)
                                .putExtra("id", result.transaksi.id.toString())
                        )

                        printLog(result.transaksi.id.toString())
                    }

                }
            }

            override fun onFailure(p0: Call<TransaksiResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun latestTransaksi(adapter: TransaksiAdapter) {
        ApiServices.endPoint.latestTransaksi().enqueue(object : Callback<TransaksiResponses>{
            override fun onResponse(
                p0: Call<TransaksiResponses>,
                p1: Response<TransaksiResponses>
            ) {
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    adapter.addDataTransaksi(p1.body()!!)
                }
            }

            override fun onFailure(p0: Call<TransaksiResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun getTransaksi(adapter: RiwayatTransaksiAdapter) {
        ApiServices.endPoint.getAllTransaksi().enqueue(object : Callback<RiwayatTransaksiResponses>{
            override fun onResponse(
                p0: Call<RiwayatTransaksiResponses>,
                p1: Response<RiwayatTransaksiResponses>
            ) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    if (result != null) {
                        adapter.setData(result.transaksi)
                        printLog(result.toString())
                    }
                }
            }

            override fun onFailure(p0: Call<RiwayatTransaksiResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun showTransaksi(id: Int, adapter: DetailRiwiayatTransaksiAdapter) {
        ApiServices.endPoint.getDetailTransaksi(id).enqueue(object : Callback<RiwayatTransaksiResponses>{
            override fun onResponse(
                p0: Call<RiwayatTransaksiResponses>,
                p1: Response<RiwayatTransaksiResponses>
            ) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())

                    if (result != null) {
                        adapter.setSingleData(result.data)
                    }
                }
            }

            override fun onFailure(p0: Call<RiwayatTransaksiResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }
}