package com.example.effectivemobile.main.data.model

data class HomeStoreItemDto(
    val id: Int = 0,
    val is_new: Boolean = false,
    val title: String = "",
    val subtitle: String = "",
    val picture: String = "",
    val is_buy: Boolean = false
)