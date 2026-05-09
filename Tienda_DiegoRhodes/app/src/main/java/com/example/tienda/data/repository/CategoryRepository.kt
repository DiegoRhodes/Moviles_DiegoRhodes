package com.example.tienda.data.repository

import com.example.tienda.data.api.ApiService
import com.example.tienda.data.api.RetrofitClient
import com.example.tienda.data.dto.CategoryDto

class CategoryRepository(private val api: ApiService = RetrofitClient.api) {
    suspend fun getCategories(): List<CategoryDto> {
        return api.getCategories()
    }
}