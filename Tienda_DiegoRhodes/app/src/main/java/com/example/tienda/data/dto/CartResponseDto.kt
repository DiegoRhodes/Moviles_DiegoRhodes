package com.example.tienda.data.dto

data class CartResponseDto(
    val items: List<CartItemDto>,
    val itemsDistinctCount: Int,
    val itemsCount: Int,
    val price: Double
)