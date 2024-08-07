package com.example.pos_demo_mvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos_demo_mvvm.data.model.auth.login.LoginResponses
import com.example.pos_demo_mvvm.data.repository.AuthRepository
import com.example.pos_demo_mvvm.ui.auth.login.LoginActivity
import com.example.pos_demo_mvvm.utils.MyResponse
import com.example.pos_demo_mvvm.utils.revokeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel() {
    val logoutResult = MutableLiveData<MyResponse<LoginResponses>>()

    fun doLogouts(context: Context) {
        logoutResult.value = MyResponse.loading()
        viewModelScope.launch {
            val responses = repository.doLogOut()

            Log.d("logout_api", responses.body().toString())

            if (responses.code() == 200) {
                revokeToken(context)
                context.startActivity(
                    Intent(context, LoginActivity::class.java)
                )
            }
        }
    }
}