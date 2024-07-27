package com.example.posdemo.responses

data class LoginResponses(
    val user: User,
    val token: String
){
    data class User(
        val id: Int,
        val name: String,
        val email: String
    )
}
