package com.example.effectivemobile.core_ui.core_network

import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.core_ui.presentation.viewmodel.BaseViewModel
import com.example.effectivemobile.network.base.error.ErrorEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> BaseViewModel.networkExecutor(networkBuilder: NetworkBuilder<T>.() -> Unit) {
    viewModelScope.networkOperation(networkBuilder)
}

fun <T> CoroutineScope.networkOperation(networkBuilder: NetworkBuilder<T>.() -> Unit) {
    this.launch {
        val builder = NetworkBuilder<T>().apply { networkBuilder() }
        try {
            builder.builderStartLambda?.invoke()

            builder.builderLoadingLiveData?.value = true

            val result = builder.builderApiCall?.invoke(this)!!

            builder.builderSuccess.invoke(result)


        } catch (e: ErrorEntity) {

            builder.builderErrorLambda?.invoke(this, e)
        } finally {
            builder.builderFinishLambda?.invoke()
            builder.builderLoadingLiveData?.value = false
        }
    }
}