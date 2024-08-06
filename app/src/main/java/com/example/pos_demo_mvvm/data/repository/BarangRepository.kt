package com.example.pos_demo_mvvm.data.repository

import android.util.Log
import com.example.pos_demo_mvvm.data.model.barang.BarangResponses
import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import com.example.pos_demo_mvvm.utils.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BarangRepository @Inject constructor(private val api: ApiEndPoint) {
    suspend fun indexBarang(): Flow<MyResponse<BarangResponses>> = flow {
        val responses = api.getAllBarang()

        if (responses.isSuccessful) {
            Log.d("data_barang", responses.body().toString())
            emit(MyResponse.success(responses.body()))
        } else {
            emit(MyResponse.error("Error"))
        }
    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }
}