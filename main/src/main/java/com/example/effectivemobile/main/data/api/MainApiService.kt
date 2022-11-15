package com.example.effectivemobile.main.data.api

import com.example.effectivemobile.main.data.model.MainDto
import retrofit2.Response
import retrofit2.http.GET

interface MainApiService {

    @GET("/v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProductInformation(): Response<MainDto>
}