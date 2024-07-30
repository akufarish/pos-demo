package com.example.posdemo.pages.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.posdemo.databinding.ActivitySplashBinding
import com.example.posdemo.pages.MainActivity
import com.example.posdemo.pages.auth.login.LoginActivity
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.retrofit.auth.TokenManager

class SplashActivity : AppCompatActivity() {
    private lateinit var _binding: ActivitySplashBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tokenManager = TokenManager(applicationContext)
        val token = tokenManager.getToken()

        if (token != null) {
            ApiServices.setToken(token)

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(this@SplashActivity, MainActivity::class.java)
                )
            }, 2000)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(this@SplashActivity, LoginActivity::class.java)
                )
            }, 2000)
        }
    }
}