package com.example.posdemo.responses

data class UserRespones(
    val user: User
) {
    data class User(
        val id: Int,
        val name: String,
        val email: String,
        val role: String
    )
}
