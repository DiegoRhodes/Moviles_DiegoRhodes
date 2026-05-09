package com.example.tienda.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tienda.data.dto.ProductDto
import com.example.tienda.data.repository.ProductRepository

class ProductState(private val productRepository: ProductRepository){
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var products by mutableStateOf<List<ProductDto>>(emptyList())
        private set

    suspend fun loadAllProducts(){
        isLoading = true
        errorMessage = null

        try {
            products = productRepository.getAllProducts()
        }catch (e: Exception){
            errorMessage = e.message ?: "Error cargando productos"
        }finally {
            isLoading = false
        }
    }

    suspend fun filterByCategory(categoryId: Long) {
        isLoading = true
        errorMessage = null

        try {
            products = productRepository.getProductsByCategory(categoryId)
        } catch (e: Exception) {
            errorMessage = e.message ?: "Error cargando productos por categorías"
        } finally {
            isLoading = false
        }
    }

}
