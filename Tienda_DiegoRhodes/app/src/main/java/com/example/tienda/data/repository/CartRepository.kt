package com.example.tienda.data.repository

import com.example.tienda.data.api.RetrofitClient
import com.example.tienda.data.dto.AddCartDto
import com.example.tienda.data.dto.CartResponseDto

class CartRepository {
    private val api = RetrofitClient.api

    suspend fun getCart(): CartResponseDto = api.getCart()

    suspend fun addProduct(productId: Long, prodQuantity: Int): CartResponseDto {
        return api.addToCart(AddCartDto(prodId = productId, prodQuantity = prodQuantity))
    }
}