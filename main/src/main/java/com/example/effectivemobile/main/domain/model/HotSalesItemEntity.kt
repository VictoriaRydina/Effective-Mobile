package com.example.effectivemobile.main.domain.model

data class HotSalesItemEntity(
    val id: Int = 0,
    val is_new: Boolean = false,
    val title: String = "",
    val subtitle: String = "",
    val picture: String = "",
    val is_buy: Boolean = false
)