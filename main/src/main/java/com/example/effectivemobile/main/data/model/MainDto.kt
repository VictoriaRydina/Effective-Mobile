package com.example.effectivemobile.main.data.model

data class MainDto(
    val home_store : List<HomeStoreItemDto> = listOf(),
    val best_seller : List<BestSellerItemDto> = listOf()
)