package com.example.effectivemobile.main.domain.model

data class MainEntity(
    val home_store : List<HomeStoreItemEntity>,
    val best_seller : List<BestSellerItemEntity>
)