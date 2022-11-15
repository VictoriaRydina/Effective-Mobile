package com.example.effectivemobile.main.presentation.viewmodel

import com.example.effectivemobile.core_ui.core_network.*
import com.example.effectivemobile.core_ui.presentation.viewmodel.BaseViewModel
import com.example.effectivemobile.main.domain.model.MainEntity
import com.example.effectivemobile.main.domain.use_case.MainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val mainUseCase: MainUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.IdleState)
    val state: StateFlow<ViewState> get() = _state

    fun getProductInformation() {
        networkExecutor<MainEntity> {
            execute {
                mainUseCase.getMainModel()
            }
            success {
                _state.value = ViewState.MainReceivedState(it)
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