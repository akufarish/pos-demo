package com.example.posdemo.requests

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
