package com.example.effectivemobile.network.base.error

import android.content.Context
import com.example.effectivemobile.network.extensions.isNetworkAvailable
import java.io.IOException

/**
 * ErrorHandler interface in domain layer, for propagating errors from data to domain layers
 */
interface ErrorHelper {
    fun getErrorByThrowable(throwable: Throwable): ErrorEntity
}

class ErrorHelperImpl(private val context: Context) : ErrorHelper {
    override fun getErrorByThrowable(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> {
                if (isInternetAvailable()) {
                    ErrorEntity.InternalServerError(throwable.message)
                } else {
                    ErrorEntity.NoConnectionError()
                }
            }
            else -> ErrorEntity.UnknownError(throwable.message)
        }
    }

    private fun isInternetAvailable(): Boolean = context.isNetworkAvailable()
}