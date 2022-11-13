package com.example.effectivemobile.product_details.data.datasource

import com.example.effectivemobile.product_details.data.api.ProductDetailsService
import com.example.effectivemobile.product_details.data.model.CartProductDto
import com.example.effectivemobile.product_details.data.model.ProductDetailsDto
import javax.inject.Inject

class ProductDetailsDataSourceImpl
@Inject constructor(
    private val productDetailsService: ProductDetailsService
): ProductDetailsDataSource{

    override suspend fun getProductDetails(): ProductDetailsDto {
        return productDetailsService.getProductDetails()
    }

    override suspend fun getCartProductList(): CartProductDto {
        return productDetailsService.getCartProductList()
    }
}