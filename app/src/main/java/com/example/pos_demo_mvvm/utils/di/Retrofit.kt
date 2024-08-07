package com.example.pos_demo_mvvm.utils.di

import android.content.Context
import android.util.Log
import com.example.pos_demo_mvvm.data.server.ApiEndPoint
import com.example.pos_demo_mvvm.utils.BASE_URL
import com.example.pos_demo_mvvm.utils.token.DefaultTokenProvider
import com.example.pos_demo_mvvm.utils.token.TokenAuthenticator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Retrofit {

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
    fun provideTokenProvider(@ApplicationContext context: Context): DefaultTokenProvider {
        return DefaultTokenProvider(context)
    }

    @Provides
    @Singleton
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenProvider: DefaultTokenProvider
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .authenticator(TokenAuthenticator(tokenProvider))
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer ${tokenProvider.getToken()}")
                    .header("Accept", "application/json")
                    .build()
                Log.d("RetrofitModule", "Making request with token: ${tokenProvider.getToken()}")
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun retrofitSetup(
        baseUrl: String,
        gson: Gson,
        client: OkHttpClient
    ): ApiEndPoint = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(ApiEndPoint::class.java)
}
