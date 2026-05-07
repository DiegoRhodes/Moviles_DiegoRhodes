package com.example.tienda.data.api

import com.example.tienda.data.dto.LoginRequest
import com.example.tienda.data.dto.LoginResponse
import com.example.tienda.data.dto.ProductDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}