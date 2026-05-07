package com.example.tienda.data.repository

import com.example.tienda.data.api.RetrofitClient
import com.example.tienda.data.dto.ProductDto

class ProductRepository {

    private val apiService = RetrofitClient.api

    suspend fun getAllProducts(): List<ProductDto> {
        return apiService.getProducts()
    }
}