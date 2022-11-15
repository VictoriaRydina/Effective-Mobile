package com.example.effectivemobile.network.base.error

import okhttp3.ResponseBody

sealed class ErrorEntity(private val errorMessage: String) :
    Throwable(errorMessage) {

    object NoError : ErrorEntity("")
    class NoConnectionError(override val message: String? = null) :
        ErrorEntity(message ?: "No network")

    data class UnknownError(override val message: String? = null) :
        ErrorEntity(message ?: "Unknown")

    class InternalServerError(override val message: String? = null) :
        ErrorEntity(message ?: "Internal server error")

    class NotFoundError(errorBody: ResponseBody) : CustomErrorEntity(errorBody, "Not found")
    class ForbiddenError(errorBody: ResponseBody) : CustomErrorEntity(errorBody, "Forbidden")
}