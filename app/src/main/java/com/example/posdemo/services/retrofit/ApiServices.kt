package com.example.posdemo.retrofit

import com.example.posdemo.retrofit.auth.DefaultTokenProvider
import com.example.posdemo.services.Common
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServices {
    const val BASE_URL: String = Common.API_URL

    private val tokenProvider = DefaultTokenProvider()

    @Singleton
    @get:Provides
    val endPoint: ApiEndPoint
        get() {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .authenticator(TokenAuthenticator(tokenProvider))
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                            .header("Authorization", "Bearer ${tokenProvider.getToken()}")
                        .header("Accept", "application/json")
                        .build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiEndPoint::class.java)
        }

    fun setToken(token: String) {
        tokenProvider.setToken(token)
    }

    fun revokeToken() {
        tokenProvider.revokeToken()
    }
}
