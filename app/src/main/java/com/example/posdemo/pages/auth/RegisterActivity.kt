package com.example.posdemo.pages.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.posdemo.databinding.ActivityRegisterBinding
import com.example.posdemo.requests.RegisterRequest
import com.example.posdemo.retrofit.ApiServices
import com.example.posdemo.services.auth.AuthServices

class RegisterActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityRegisterBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.loginButton.setOnClickListener {
            startActivity(
                Intent(this@RegisterActivity, LoginActivity::class.java)
            )
        }

        binding.registerButton.setOnClickListener {
            val nameValue = binding.phoneEditText.text.toString()
            val emailValue = binding.emailEditText.text.toString()
            val passwordValue = binding.passwordEditText.text.toString()

            val payload = RegisterRequest(
                name = nameValue,
                email = emailValue,
                password = passwordValue
            )

            AuthServices.register(payload)
        }
    }
}