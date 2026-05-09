package com.example.tienda.data.api

import com.example.tienda.data.dto.CategoryDto
import com.example.tienda.data.dto.LoginRequest
import com.example.tienda.data.dto.LoginResponse
import com.example.tienda.data.dto.ProductDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("api/v1/products")
    suspend fun getProducts(): List<ProductDto>

    @GET("api/v1/categories/{categoryId}/products")
    suspend fun getProductsByCategory(@Path("categoryId") categoryId: Long): List<ProductDto>

    @GET("api/v1/categories")
    suspend fun getCategories(): List<CategoryDto>




}