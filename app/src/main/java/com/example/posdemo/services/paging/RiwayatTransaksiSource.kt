package com.example.posdemo.services.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.posdemo.responses.RiwayatTransaksiResponses
import com.example.posdemo.retrofit.ApiEndPoint

class RiwayatTransaksiSource (val apiEndPoint: ApiEndPoint): PagingSource<Int, RiwayatTransaksiResponses.Transaksi>() {
    override fun getRefreshKey(state: PagingState<Int, RiwayatTransaksiResponses.Transaksi>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RiwayatTransaksiResponses.Transaksi> {
        return try {
            val position = params.key ?: 1
            val responses = apiEndPoint.getAllTransaksi(position)
            return  LoadResult.Page(
                data = responses.data,
                prevKey = if (position  == 1) null else position - 1,
                nextKey = if (position == responses.total) null else position + 1
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}