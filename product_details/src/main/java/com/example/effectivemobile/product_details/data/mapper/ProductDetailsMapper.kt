package com.example.effectivemobile.product_details.data.mapper

import com.example.effectivemobile.core_ui.mapper.Mapper
import com.example.effectivemobile.product_details.data.model.ProductDetailsDto
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity
import javax.inject.Inject

class ProductDetailsMapper
@Inject constructor() : Mapper<ProductDetailsDto, ProductDetailsEntity> {

    override fun map(input: ProductDetailsDto): ProductDetailsEntity = ProductDetailsEntity(
        CPU = input.CPU,
        camera = input.camera,
        capacity = input.capacity,
        color = input.color,
        id = input.id,
        images = input.images,
        isFavorites = input.isFavorites,
        price = input.price,
        rating = input.rating,
        sd = input.sd,
        ssd = input.ssd,
        title = input.title
    )
}