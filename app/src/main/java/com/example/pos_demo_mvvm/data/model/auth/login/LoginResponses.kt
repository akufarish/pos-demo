package com.example.pos_demo_mvvm.data.model.auth.login

data class LoginResponses(
    val user: User,
    val token: String
) {
    data class User(
        val id: Int,
        val name: String,
        val email: String
    )
}
