package com.example.effectivemobile.main.data.source

import com.example.effectivemobile.main.data.api.MainApiService
import com.example.effectivemobile.main.data.model.MainDto
import javax.inject.Inject

class MainDataSourceImpl
@Inject constructor(
    private val mainApiService: MainApiService
) : MainDataSource{

    override suspend fun getProductInformation(): MainDto {
        return mainApiService.getProductInformation()
    }
}