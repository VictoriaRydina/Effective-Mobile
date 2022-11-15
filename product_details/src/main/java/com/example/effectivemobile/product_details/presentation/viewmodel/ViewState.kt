package com.example.effectivemobile.product_details.presentation.viewmodel

import com.example.effectivemobile.network.base.error.ErrorEntity
import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity

sealed class ViewState {
    data class CartProductState(val data: CartProductEntity) : ViewState()
    data class ProductDetailsState(val data: ProductDetailsEntity) : ViewState()
    data class ErrorState(val data: ErrorEntity) : ViewState()
    object IdleState : ViewState()
}