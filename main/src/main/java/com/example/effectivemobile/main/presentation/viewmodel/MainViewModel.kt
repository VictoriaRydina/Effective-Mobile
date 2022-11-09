package com.example.effectivemobile.main.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.core_ui.BaseViewModel
import com.example.effectivemobile.main.domain.model.MainEntity
import com.example.effectivemobile.main.domain.use_case.MainUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val mainUseCase: MainUseCase
): BaseViewModel() {

    val mainLD: MutableLiveData<MainEntity> = MutableLiveData()

    fun getProductInformation() {
        viewModelScope.launch {
            val model = withContext(Dispatchers.IO) {
                mainUseCase.getMainModel()
            }
            mainLD.value = model
        }
    }
}