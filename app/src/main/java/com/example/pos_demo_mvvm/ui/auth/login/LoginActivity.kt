package com.example.pos_demo_mvvm.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.data.model.auth.login.LoginRequest
import com.example.pos_demo_mvvm.databinding.ActivityLoginBinding
import com.example.pos_demo_mvvm.ui.auth.register.RegisterActivity
import com.example.pos_demo_mvvm.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.loginButton?.setOnClickListener {
            doLogin()
        }

        binding?.registerButton?.setOnClickListener {
            startActivity(
                Intent(this@LoginActivity, RegisterActivity::class.java)
            )
        }
    }

    private fun doLogin() {
        val payload = LoginRequest(
            email = binding?.phoneEditText?.text.toString(),
            password = binding?.passwordEditText?.text.toString()
        )
        viewModel.doLogin(payload, applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}