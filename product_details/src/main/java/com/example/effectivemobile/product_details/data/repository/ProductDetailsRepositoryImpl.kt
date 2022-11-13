package com.example.effectivemobile.product_details.data.repository

import com.example.effectivemobile.product_details.data.datasource.ProductDetailsDataSource
import com.example.effectivemobile.product_details.data.mapper.CartProductMapper
import com.example.effectivemobile.product_details.data.mapper.ProductDetailsMapper
import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity
import com.example.effectivemobile.product_details.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class ProductDetailsRepositoryImpl
@Inject constructor(
    private val productDetailsDataSource: ProductDetailsDataSource,
    private val productDetailsMapper: ProductDetailsMapper,
    private val cartProductMapper: CartProductMapper
) : ProductDetailsRepository {

    override suspend fun getProductDetails(): ProductDetailsEntity {
        return productDetailsDataSource.getProductDetails().let {
            productDetailsMapper.map(it)
        }
    }

    override suspend fun getCartProductList(): CartProductEntity {
        return productDetailsDataSource.getCartProductList().let {
            cartProductMapper.map(it)
        }
    }
}