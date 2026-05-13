package com.example.tienda.data.dto

data class CartItemDto(
    val prodId: Long,
    val name: String,
    val price: Double,
    val discount: Int,
    val priceDiscount: Double,
    val uni: Int,
    val totalPrice: Double
)