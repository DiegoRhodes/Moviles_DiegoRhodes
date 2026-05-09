package com.example.tienda.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tienda.data.dto.CategoryDto
import com.example.tienda.data.repository.CategoryRepository

class CategoryState(private val categoryRepository: CategoryRepository) {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var categories by mutableStateOf<List<CategoryDto>>(emptyList())
        private set

    suspend fun loadCategories() {
        isLoading = true
        errorMessage = null

        try {
            categories = categoryRepository.getCategories()
        } catch (e: Exception) {
            errorMessage = e.message ?: "Error cargando categorías"
        } finally {
            isLoading = false
        }
    }
}