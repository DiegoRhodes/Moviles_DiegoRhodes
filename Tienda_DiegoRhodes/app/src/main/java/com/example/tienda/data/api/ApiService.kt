package com.example.tienda.data.api

import com.example.tienda.data.dto.LoginRequest
import com.example.tienda.data.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}