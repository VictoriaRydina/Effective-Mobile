package com.example.effectivemobile.network.base

import com.example.effectivemobile.network.base.error.CustomErrorEntity
import com.example.effectivemobile.network.base.error.ErrorEntity
import com.example.effectivemobile.network.base.error.ErrorHelper
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.*

class NetworkHelperImpl(private val errorHelper: ErrorHelper) : NetworkHelper {

    override suspend fun <SERVICE_DATA : Any> apiExecute(
        apiCall: suspend () -> Response<SERVICE_DATA>
    ): SERVICE_DATA {
        return try {
            val response = apiCall.invoke()

            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw getErrorByResponseCode(response.code(), response.errorBody()!!)
            }
        } catch (throwable: Throwable) {
            if (throwable is CustomErrorEntity) throw throwable
            throw errorHelper.getErrorByThrowable(throwable)
        }
    }

    private fun getErrorByResponseCode(responseCode: Int, responseBody: ResponseBody): ErrorEntity {
        return when (responseCode) {
            HTTP_NOT_FOUND -> ErrorEntity.NotFoundError(responseBody)
            HTTP_FORBIDDEN -> ErrorEntity.ForbiddenError(responseBody)
            else -> ErrorEntity.UnknownError("Something go wrong")
        }
    }
}