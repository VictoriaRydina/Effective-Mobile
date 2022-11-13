package com.example.effectivemobile.product_details.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.core_ui.presentation.viewmodel.BaseViewModel
import com.example.effectivemobile.product_details.domain.model.CartProductEntity
import com.example.effectivemobile.product_details.domain.model.ProductDetailsEntity
import com.example.effectivemobile.product_details.domain.use_case.CartProductUseCase
import com.example.effectivemobile.product_details.domain.use_case.ProductDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsProductViewModel
@Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase,
    private val cartProductUseCase: CartProductUseCase
) : BaseViewModel() {

    val productDetailsLD: MutableLiveData<ProductDetailsEntity> = MutableLiveData()
    val cartProductLD: MutableLiveData<CartProductEntity> = MutableLiveData()

    fun getProductDetails() {
        viewModelScope.launch {
            val model = withContext(Dispatchers.IO) {
                productDetailsUseCase.getProductDetailsModel()
            }
            productDetailsLD.value = model
        }
    }

    fun getCartProductList() {
        viewModelScope.launch {
            val model = withContext(Dispatchers.IO) {
                cartProductUseCase.getCartProductModel()
            }
            cartProductLD.value = model
        }
    }
}