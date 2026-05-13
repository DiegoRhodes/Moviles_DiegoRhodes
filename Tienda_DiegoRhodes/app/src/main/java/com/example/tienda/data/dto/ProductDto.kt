package com.example.tienda.data.dto

data class ProductDto(
    val prodId: String,
    val prodCode: String,
    val prodName: String,
    val prodBrand: String,
    val prodDesc: String,
    val prodImage: String,
    val prodPrice: Double,
    val prodDiscount: Int,
    val prodStock: Int,
    val categories: List<CategoryDto>
)