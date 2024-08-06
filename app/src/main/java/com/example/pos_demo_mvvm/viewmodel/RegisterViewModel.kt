package com.example.pos_demo_mvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.model.auth.register.RegisterRequest
import com.example.pos_demo_mvvm.data.repository.AuthRepository
import com.example.pos_demo_mvvm.ui.auth.login.LoginActivity
import com.example.pos_demo_mvvm.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {
    val registerResult = MutableLiveData<MyResponse<LoginResponses>>()

    fun doRegister(payload: RegisterRequest, context: Context) {
        registerResult.value = MyResponse.loading()
        viewModelScope.launch {
            try {
                val payload = RegisterRequest(
                    email = payload.email,
                    name = payload.name,
                    password = payload.email
                )
                val responses = repository.doRegister(payload)

                Log.d("responses_api", responses.code().toString())
                if (responses.code() == 201) {
                    context.startActivity(
                        Intent(context, LoginActivity::class.java)
                    )
                }
            } catch (e: Exception) {
                Log.d("error_api", e.toString())
            }
        }
    }
}