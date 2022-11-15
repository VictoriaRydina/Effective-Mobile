package com.example.effectivemobile.main.presentation.viewmodel

import com.example.effectivemobile.main.domain.model.MainEntity
import com.example.effectivemobile.network.base.error.ErrorEntity

sealed class ViewState {
    data class MainReceivedState(val data: MainEntity) : ViewState()
    data class ErrorState(val data: ErrorEntity) : ViewState()
    object IdleState : ViewState()
}