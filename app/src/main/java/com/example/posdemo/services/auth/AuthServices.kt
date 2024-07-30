package com.example.posdemo.services.auth

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.posdemo.databinding.FragmentProdukBinding
import com.example.posdemo.pages.MainActivity
import com.example.posdemo.pages.auth.login.LoginActivity
import com.example.posdemo.pages.produk.tambahProduk.TambahProdukActivity
import com.example.posdemo.requests.LoginRequest
import com.example.posdemo.requests.RegisterRequest
import com.example.posdemo.responses.LoginResponses
import com.example.posdemo.responses.UserRespones
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.retrofit.auth.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthServices {
    fun login(payload: LoginRequest, context: Context) {
        ApiServices.endPoint.login(payload).enqueue(object: Callback<LoginResponses>{
            override fun onResponse(p0: Call<LoginResponses>, p1: Response<LoginResponses>) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())

                    if (result != null) {
                        setToken(result, context)
                        Handler(Looper.getMainLooper()).postDelayed({
                            context.startActivity(
                                Intent(context, MainActivity::class.java)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            )
                        }, 2000)
                        Toast.makeText(context, "Login sukses", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(p0: Call<LoginResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun register(payload: RegisterRequest, context: Context) {
        ApiServices.endPoint.register(payload).enqueue(object: Callback<LoginResponses>{
            override fun onResponse(p0: Call<LoginResponses>, p1: Response<LoginResponses>) {
                if (p1.isSuccessful) {
                    printLog(p1.body().toString())
                    context.startActivity(
                        Intent(context, LoginActivity::class.java)
                    )
                }
            }

            override fun onFailure(p0: Call<LoginResponses>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun printLog(msg: String) {
        Log.d("auth_api", msg)
    }

    private fun setToken(data: LoginResponses, context: Context) {
        ApiServices.setToken(data.token)
        printLog(data.token)
        val tokenManager = TokenManager(context)
        tokenManager.saveToken(data.token)
    }

    private fun revokeToken(context: Context) {
        ApiServices.revokeToken()
        val tokenManager = TokenManager(context)
        tokenManager.clearToken()
    }

    fun getAuth(binding: FragmentProdukBinding, context: Context) {
        ApiServices.endPoint.getAuthProfile().enqueue(object : Callback<UserRespones>{
            override fun onResponse(p0: Call<UserRespones>, p1: Response<UserRespones>) {
                val result = p1.body()
                if (p1.isSuccessful) {
                    if (result != null) {
                        if (result.user.role == "merchant") {
                            binding.fab.visibility = View.VISIBLE
                            binding.fab.setOnClickListener {
                                context.startActivity(
                                    Intent(context, TambahProdukActivity::class.java)
                                )
                            }
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<UserRespones>, p1: Throwable) {
                printLog(p1.toString())
            }
        })
    }

    fun doLogOut(context: Context) {
        ApiServices.endPoint.doLogout().enqueue(object : Callback<LoginResponses> {
            override fun onResponse(p0: Call<LoginResponses>, p1: Response<LoginResponses>) {
                if (p1.isSuccessful) {
                    revokeToken(context)
                    Handler(Looper.getMainLooper()).postDelayed({
                        context.startActivity(
                            Intent(context, LoginActivity::class.java)
                        )
                    }, 2000)
                }
            }

            override fun onFailure(p0: Call<LoginResponses>, p1: Throwable) {
                printLog(p1.toString())
            }

        })
    }
}