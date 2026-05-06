package com.example.tienda.data.repository

import com.example.tienda.data.api.RetrofitClient
import com.example.tienda.data.dto.LoginRequest

class TiendaRepository {
    private val api = RetrofitClient.api

    suspend fun login(user: String, pass: String) =
        api.login(LoginRequest(user, pass))
}