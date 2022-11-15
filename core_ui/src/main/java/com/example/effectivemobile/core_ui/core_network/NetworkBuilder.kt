package com.example.effectivemobile.core_ui.core_network

import com.example.effectivemobile.core_ui.SingleLiveEvent
import com.example.effectivemobile.network.base.error.ErrorEntity
import kotlinx.coroutines.CoroutineScope

class NetworkBuilder<T> {
    internal var builderLoadingLiveData : SingleLiveEvent<Boolean>? = null
    internal var builderApiCall: (suspend CoroutineScope.() -> T)? = null
    internal var builderStartLambda: (() -> Unit)? = null
    internal var builderFinishLambda: (() -> Unit)? = null
    internal var builderSuccess: ((result: T) -> Unit) = {}
    internal var builderErrorLambda: (suspend CoroutineScope.(ErrorEntity) -> Unit)? = null
}

fun <T> NetworkBuilder<T>.execute(execution: suspend CoroutineScope.() -> T) {
    this.builderApiCall = execution
}

fun <T> NetworkBuilder<T>.error(error: suspend CoroutineScope.(ErrorEntity) -> Unit) {
    this.builderErrorLambda = error
}

fun <T> NetworkBuilder<T>.success(success: (T) -> Unit) {
    this.builderSuccess = success
}

fun <T> NetworkBuilder<T>.loading(loading: SingleLiveEvent<Boolean>){
    this.builderLoadingLiveData = loading
}