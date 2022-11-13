package com.example.effectivemobile.product_details.domain.model

data class ProductDetailsEntity(
    val CPU: String = "",
    val camera: String = "",
    val capacity: List<String> = listOf(),
    val color: List<String> = listOf(),
    val id: String = "",
    val images: List<String>,
    val isFavorites: Boolean = true,
    val price: Int = 0,
    val rating: Double = 0.0,
    val sd: String = "",
    val ssd: String = "",
    val title: String = ""
)