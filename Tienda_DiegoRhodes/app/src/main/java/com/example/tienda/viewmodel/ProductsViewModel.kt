package com.example.tienda.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.repository.ProductRepository
import com.example.tienda.states.ProductState
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val state = ProductState(ProductRepository())

    val isLoading get() = state.isLoading
    val errorMessage get() = state.errorMessage
    val products get() = state.products

    fun loadAllProducts() {
        viewModelScope.launch {
            state.loadAllProducts()
        }
    }

    fun loadProductsByCategory(categoryId: Long) {
        viewModelScope.launch {
            state.filterByCategory(categoryId)
        }
    }
}