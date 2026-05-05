package com.example.tienda.data.dto

data class LoginResponse (
    val accessToken: String,
    val refreshToken: String
)