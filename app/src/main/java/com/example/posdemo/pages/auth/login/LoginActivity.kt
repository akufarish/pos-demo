package com.example.posdemo.pages.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posdemo.databinding.ActivityLoginBinding
import com.example.posdemo.pages.auth.register.RegisterActivity
import com.example.posdemo.requests.LoginRequest
import com.example.posdemo.services.auth.AuthServices

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.registerButton.setOnClickListener {
            startActivity(
                Intent(this@LoginActivity, RegisterActivity::class.java)
            )
        }

        binding.loginButton.setOnClickListener {
            val emailText = binding.phoneEditText.text.toString()
            val passwordText = binding.passwordEditText.text.toString()
            val payload = LoginRequest(email = emailText, password = passwordText)

            AuthServices.login(payload, applicationContext)
        }
    }
}