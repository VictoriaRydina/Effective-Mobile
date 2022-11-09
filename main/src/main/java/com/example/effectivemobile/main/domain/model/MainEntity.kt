package com.example.effectivemobile.main.domain.model

data class MainEntity(
    val home_store : List<HotSalesItemEntity>,
    val best_seller : List<BestSellerItemEntity>
)