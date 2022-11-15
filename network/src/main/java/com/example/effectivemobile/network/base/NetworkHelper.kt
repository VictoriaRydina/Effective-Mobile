package com.example.effectivemobile.network.base

import retrofit2.Response

interface NetworkHelper {
    /**
     * @param DTO is a returning response type (DTO)
     * @param SERVICE_DATA is a response from service which contains data [DTO] and status code
     * @param apiCall is a suspend lambda function, which returns some data from remote source
     */
    suspend fun <SERVICE_DATA : Any> apiExecute(apiCall: suspend () -> Response<SERVICE_DATA>): SERVICE_DATA
}