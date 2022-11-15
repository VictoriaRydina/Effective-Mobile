package com.example.effectivemobile.product_details.presentation.viewmodel

import com.example.effectivemobile.core_ui.core_network.*
import com.example.effectivemobile.core_ui.presentation.viewmodel.BaseViewModel
import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity
import com.example.effectivemobile.product_details.domain.use_case.CartProductUseCase
import com.example.effectivemobile.product_details.domain.use_case.ProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailsProductViewModel
@Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase,
    private val cartProductUseCase: CartProductUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.IdleState)
    val state: StateFlow<ViewState> get() = _state

    fun getProductDetails() {
        networkExecutor<ProductDetailsEntity> {
            execute {
                productDetailsUseCase.getProductDetailsModel()
            }
            success {
                _state.value = ViewState.ProductDetailsState(it)
            }
            error {
                _state.value = ViewState.ErrorState(it)
            }
            loading(_loading)
        }
    }

    fun getCartProductList() {
        networkExecutor<CartProductEntity> {
            execute {
                cartProductUseCase.getCartProductModel()
            }
            success {
                _state.value = ViewState.CartProductState(it)
            }
            error {
                _state.value = ViewState.ErrorState(it)
            }
            loading(_loading)
        }
    }

    fun setIdleState() {
        _state.value = ViewState.IdleState
    }
}