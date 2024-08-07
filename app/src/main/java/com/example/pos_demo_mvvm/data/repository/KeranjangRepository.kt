package com.example.pos_demo_mvvm.data.repository

import android.util.Log
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangRequest
import com.example.pos_demo_mvvm.data.model.keranjang.KeranjangResponses
import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import com.example.pos_demo_mvvm.utils.MyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class KeranjangRepository @Inject constructor(private val api: ApiEndPoint) {
    suspend fun storeKeranjang(id: Int, payload: KeranjangRequest): Response<KeranjangResponses> {
        return api.StoreKeranjang(id, payload)
    }

    suspend fun indexKeranjang(): Flow<MyResponse<KeranjangResponses>> = flow {
        val responses = api.indexKeranjang()

        if (responses.isSuccessful) {
            Log.d("keranjang_api", responses.body().toString())
            emit(MyResponse.success(responses.body()))
        } else {
            emit(MyResponse.error("Error"))
        }
    }.catch {
        emit(MyResponse.error(it.message.toString()))
    }
}