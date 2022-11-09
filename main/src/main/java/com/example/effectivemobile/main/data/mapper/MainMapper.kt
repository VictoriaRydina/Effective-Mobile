package com.example.effectivemobile.main.data.mapper

import com.example.effectivemobile.core_ui.mapper.Mapper
import com.example.effectivemobile.main.data.model.BestSellerItemDto
import com.example.effectivemobile.main.data.model.HomeStoreItemDto
import com.example.effectivemobile.main.data.model.MainDto
import com.example.effectivemobile.main.domain.model.BestSellerItemEntity
import com.example.effectivemobile.main.domain.model.HomeStoreItemEntity
import com.example.effectivemobile.main.domain.model.MainEntity
import javax.inject.Inject

class MainMapper
@Inject constructor() : Mapper<MainDto, MainEntity> {

    override fun map(input: MainDto) = MainEntity(
        home_store = input.home_store.map { mapHomeStore(it) },
        best_seller = input.best_seller.map { mapBestSeller(it) }
    )

    private fun mapHomeStore(input: HomeStoreItemDto): HomeStoreItemEntity =
        HomeStoreItemEntity(
            id = input.id,
            is_new = input.is_new,
            title = input.title,
            subtitle = input.subtitle,
            picture = input.picture,
            is_buy = input.is_buy
        )

    private fun mapBestSeller(input: BestSellerItemDto): BestSellerItemEntity =
        BestSellerItemEntity(
            id = input.id,
            is_favorites = input.is_favorites,
            title = input.title,
            price_without_discount = input.price_without_discount,
            discount_price = input.discount_price,
            picture = input.picture
        )
}