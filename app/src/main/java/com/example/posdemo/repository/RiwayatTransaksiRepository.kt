package com.example.posdemo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.posdemo.retrofit.ApiEndPoint
import com.example.posdemo.services.paging.RiwayatTransaksiSource
import javax.inject.Inject

class RiwayatTransaksiRepository @Inject constructor (
    private val apiEndPoint: ApiEndPoint
) {
    fun getTransaksi() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {RiwayatTransaksiSource(apiEndPoint)}
    ).liveData
}