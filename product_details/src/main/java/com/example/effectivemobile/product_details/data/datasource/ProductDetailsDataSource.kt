package com.example.effectivemobile.product_details.data.datasource

import com.example.effectivemobile.product_details.data.model.CartProductDto
import com.example.effectivemobile.product_details.data.model.ProductDetailsDto

interface ProductDetailsDataSource {

    suspend fun getProductDetails(): ProductDetailsDto

    suspend fun getCartProductList(): CartProductDto
}