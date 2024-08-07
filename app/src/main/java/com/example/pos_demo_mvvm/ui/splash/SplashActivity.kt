package com.example.pos_demo_mvvm.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.pos_demo_mvvm.databinding.ActivitySplashBinding
import com.example.pos_demo_mvvm.ui.MainActivity
import com.example.pos_demo_mvvm.ui.auth.login.LoginActivity
import com.example.pos_demo_mvvm.utils.token.TokenManager

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val tokenManager = TokenManager(applicationContext)
        val token = tokenManager.getToken()

        Log.d("token_api", token.toString())

        if (token != null) {

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