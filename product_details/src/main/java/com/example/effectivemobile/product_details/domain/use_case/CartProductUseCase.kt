package com.example.effectivemobile.product_details.domain.use_case

import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class CartProductUseCase
@Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) {
    suspend fun getCartProductModel(): CartProductEntity {
        return productDetailsRepository.getCartProductList()
    }
}