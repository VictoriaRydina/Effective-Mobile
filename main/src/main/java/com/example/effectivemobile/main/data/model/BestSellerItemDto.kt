package com.example.effectivemobile.main.data.model

data class BestSellerItemDto(
    val id: Int = 0,
    val is_favorites: Boolean = false,
    val title: String = "",
    val price_without_discount: Int = 0,
    val discount_price: Int = 0,
    val picture: String = ""
)