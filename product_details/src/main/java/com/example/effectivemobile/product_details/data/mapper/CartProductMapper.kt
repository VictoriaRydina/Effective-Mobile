package com.example.effectivemobile.product_details.data.mapper

import com.example.effectivemobile.core_ui.mapper.Mapper
import com.example.effectivemobile.product_details.data.model.BasketItemDto
import com.example.effectivemobile.product_details.data.model.CartProductDto
import com.example.effectivemobile.product_details.domain.model.BasketItemEntity
import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import javax.inject.Inject

class CartProductMapper
@Inject constructor() : Mapper<CartProductDto, CartProductEntity> {

    override fun map(input: CartProductDto): CartProductEntity = CartProductEntity(
        basket = input.basket.map { mapBasket(it) },
        delivery = input.delivery,
        id = input.id,
        total = input.total
    )

    private fun mapBasket(input: BasketItemDto): BasketItemEntity = BasketItemEntity(
        id = input.id,
        images = input.images,
        price = input.price,
        title = input.title
    )
}