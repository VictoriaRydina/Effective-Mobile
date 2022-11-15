package com.example.effectivemobile.product_details.data.datasource

import com.example.effectivemobile.network.base.NetworkHelper
import com.example.effectivemobile.product_details.data.api.ProductDetailsService
import com.example.effectivemobile.product_details.data.model.CartProductDto
import com.example.effectivemobile.product_details.data.model.ProductDetailsDto
import javax.inject.Inject

class ProductDetailsDataSourceImpl
@Inject constructor(
    private val productDetailsService: ProductDetailsService,
    private val networkHelper: NetworkHelper
) : ProductDetailsDataSource {

    override suspend fun getProductDetails(): ProductDetailsDto {
        return networkHelper.apiExecute {
            productDetailsService.getProductDetails()
        }
    }

    override suspend fun getCartProductList(): CartProductDto {
        return networkHelper.apiExecute {
            productDetailsService.getCartProductList()
        }
    }
}