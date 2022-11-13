package com.example.effectivemobile.product_details.data.model

data class CartProductDto(
    val basket: List<BasketItemDto> = listOf(),
    val delivery: String = "",
    val id: String = "",
    val total: Int = 0
)