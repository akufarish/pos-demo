package com.example.pos_demo_mvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.auth.login.LoginRequest
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.repository.AuthRepository
import com.example.pos_demo_mvvm.ui.MainActivity
import com.example.pos_demo_mvvm.utils.MyResponse
import com.example.pos_demo_mvvm.utils.setToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {
    val loginResult = MutableLiveData<MyResponse<LoginResponses>>()

    fun doLogin(payload: LoginRequest, context: Context) {
        loginResult.value = MyResponse.loading()
        viewModelScope.launch {
            try {
                val payload = LoginRequest(
                    email = payload.email,
                    password = payload.password
                )
                val responses = repository.doLogin(payload)

                val body = responses.body()

                if (responses.isSuccessful) {
                    if (body != null) {
                        setToken(body.token, context)
                    }
                }

                Log.d("response_api", responses.code().toString())

                if (responses.code() == 200) {
                    context.startActivity(
                        Intent(context, MainActivity::class.java)
                    )
                }
            }
            catch (e: Exception) {
                Log.d("error_api", e.toString())
            }
        }
    }
}