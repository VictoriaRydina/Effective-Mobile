package com.example.effectivemobile.core_ui.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.effectivemobile.core_ui.SingleLiveEvent
import com.example.effectivemobile.network.base.error.ErrorEntity

abstract class BaseViewModel: ViewModel() {

    val toastLD: MutableLiveData<String> = MutableLiveData()
    val toastResLD: MutableLiveData<Int> = MutableLiveData()

    val _loading = SingleLiveEvent<Boolean>()

    val error = SingleLiveEvent<ErrorEntity>()
}