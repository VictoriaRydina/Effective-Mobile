package com.example.effectivemobile.product_details.domain.repository

import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity

interface ProductDetailsRepository {

    suspend fun getProductDetails(): ProductDetailsEntity

    suspend fun getCartProductList(): CartProductEntity
}