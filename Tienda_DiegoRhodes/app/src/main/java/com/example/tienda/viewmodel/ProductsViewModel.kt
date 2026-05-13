package com.example.tienda.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.api.RetrofitClient
import com.example.tienda.data.dto.ProductDto
import com.example.tienda.data.repository.ProductRepository
import com.example.tienda.states.ProductState
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val state = ProductState(ProductRepository())

    var currentPage by mutableStateOf(0)
        private set

    private val pageSize = 5

    var selectedProduct by mutableStateOf<ProductDto?>(null)
        private set

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage

    val productsToShow: List<ProductDto>
        get() {
            val allProducts = state.products
            val start = currentPage * pageSize
            val end = minOf(start + pageSize, allProducts.size)

            return if (start < allProducts.size) {
                allProducts.subList(start, end)
            } else {
                emptyList()
            }
        }

    fun loadProductById(productId: Long) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getProductById(productId)
                selectedProduct = response
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error: ${e.message}")
                // Si hay un error (como el 403), ponemos un producto vacío o
                // mandamos un mensaje para que no se quede el cargando infinito
                selectedProduct = null
            }
        }
    }

    fun loadAllProducts() {
        currentPage = 0
        viewModelScope.launch {
            state.loadAllProducts()
        }
    }

    fun loadProductsByCategory(categoryId: Long) {
        currentPage = 0
        viewModelScope.launch {
            state.filterByCategory(categoryId)
        }
    }

    fun nextPage() {
        if ((currentPage + 1) * pageSize < state.products.size) {
            currentPage++
        }
    }

    fun previousPage() {
        if (currentPage > 0) {
            currentPage--
        }
    }

    fun hasNextPage(): Boolean {
        return (currentPage + 1) * pageSize < state.products.size
    }
}