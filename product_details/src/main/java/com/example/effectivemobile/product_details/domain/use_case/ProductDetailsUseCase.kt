package com.example.effectivemobile.product_details.domain.use_case

import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity
import com.example.effectivemobile.product_details.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class ProductDetailsUseCase
@Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) {
    suspend fun getProductDetailsModel() : ProductDetailsEntity {
        return productDetailsRepository.getProductDetails()
    }
}