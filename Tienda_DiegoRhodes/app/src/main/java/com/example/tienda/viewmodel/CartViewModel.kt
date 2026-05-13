package com.example.tienda.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.dto.CartResponseDto
import com.example.tienda.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = CartRepository()

    var cartResponse by mutableStateOf<CartResponseDto?>(null)
    var isLoading by mutableStateOf(false)
    var isAddedSuccess by mutableStateOf(false)

    fun loadCart() {
        viewModelScope.launch {
            isLoading = true
            try {
                cartResponse = repository.getCart()
            } catch (e: Exception) {
                Log.e("CART_ERROR", "Error cargando carrito: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun addProduct(productId: Long, prodQuantity: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                Log.d("CARRITO", "Enviando: ID=$productId, Units=$prodQuantity")
                val response = repository.addProduct(productId, prodQuantity)
                cartResponse = response
                isAddedSuccess = true
                Log.d("CARRITO", "¡Éxito! Navegando atrás...")
            } catch (e: Exception) {
                Log.e("CARRITO_ERROR", "ERROR FATAL: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
    fun removeItem(productId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.removeItem(productId)
                cartResponse = response
                Log.d("CARRITO", "Producto $productId eliminado correctamente")
            } catch (e: Exception) {
                Log.e("CARRITO_ERROR", "Error al eliminar producto: ${e.message}")
            } finally {
            }
        }
    }
}