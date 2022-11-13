package com.example.effectivemobile.product_details.data.api

import com.example.effectivemobile.product_details.data.model.CartProductDto
import com.example.effectivemobile.product_details.data.model.ProductDetailsDto
import retrofit2.http.GET

interface ProductDetailsService {

    @GET("/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetails(): ProductDetailsDto

    @GET("/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCartProductList(): CartProductDto
}