package com.example.effectivemobile.product_details.domain.model

data class CartProductEntity(
    val basket: List<BasketItemEntity> = listOf(),
    val delivery: String = "",
    val id: String = "0",
    val total: Int = 0
)