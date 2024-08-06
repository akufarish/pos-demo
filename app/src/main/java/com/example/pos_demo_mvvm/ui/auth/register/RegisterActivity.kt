package com.example.pos_demo_mvvm.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.pos_demo_mvvm.R
import com.example.pos_demo_mvvm.data.model.auth.register.RegisterRequest
import com.example.pos_demo_mvvm.databinding.ActivityRegisterBinding
import com.example.pos_demo_mvvm.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.registerButton?.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        val payload = RegisterRequest(
            email = binding?.emailEditText?.text.toString(),
            name = binding?.phoneEditText?.text.toString(),
            password = binding?.passwordEditText?.text.toString()
        )
        viewModel.doRegister(payload, this@RegisterActivity)
    }
}