package com.example.pos_demo_mvvm.utils.di

import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import com.example.pos_demo_mvvm.utils.BASE_URL
import com.example.pos_demo_mvvm.utils.token.DefaultTokenProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Retrofit {
    private val tokenProvider = DefaultTokenProvider()

    @Provides
    @Singleton
    fun provideUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClient() = OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .addInterceptor({ chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ${tokenProvider.getToken()}")
                .header("Accept", "application/json")
                .build()
            chain.proceed(request)
        })
        .build()

    @Provides
    @Singleton
    fun retrofitSetup(baseUrl: String, gson: Gson, client: OkHttpClient): ApiEndPoint =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
            .client(client).build().create(ApiEndPoint::class.java)

    fun setToken(token: String) {
        tokenProvider.setToken(token)
    }
}