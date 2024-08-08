package com.example.pos_demo_mvvm.data.repository

import android.util.Log
import com.example.pos_demo_mvvm.data.model.transaksi.TransaksiResponses
import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import com.example.pos_demo_mvvm.utils.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class TransaksiRepository @Inject constructor(private val api: ApiEndPoint) {
    suspend fun indexTransaksi(): Flow<MyResponse<TransaksiResponses>> = flow {
        val response = api.indexRiwayatTransaksi()

        if (response.isSuccessful) {
            Log.d("transaksi_api", response.body().toString())
            emit(MyResponse.success(response.body()))
        } else {
            emit(MyResponse.error("Error"))
        }
    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }
}