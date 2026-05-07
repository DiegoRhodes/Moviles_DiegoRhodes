package com.example.tienda.viewmodel

import android.util.Log
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

    fun loadProducts() {
        viewModelScope.launch {

            // 🔥 LOG IMPORTANTE
            Log.d("PRODUCTS_VM", "Llamando a loadAllProducts()")

            Log.d("TOKEN_CHECK", "TOKEN = ${com.example.tienda.domain.SessionManager.token}")

            try {
                state.loadAllProducts()
            } catch (e: Exception) {
                Log.e("PRODUCTS_VM", "ERROR: ${e.message}")
            }
        }
    }
}