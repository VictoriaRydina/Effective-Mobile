package com.example.effectivemobile.core_ui.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val toastLD: MutableLiveData<String> = MutableLiveData()
    val toastResLD: MutableLiveData<Int> = MutableLiveData()
}