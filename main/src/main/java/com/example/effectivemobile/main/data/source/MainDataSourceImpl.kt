package com.example.effectivemobile.main.data.source

import com.example.effectivemobile.main.data.api.MainApiService
import com.example.effectivemobile.main.data.model.MainDto
import com.example.effectivemobile.network.base.NetworkHelper
import javax.inject.Inject

class MainDataSourceImpl
@Inject constructor(
    private val mainApiService: MainApiService,
    private val networkHelper: NetworkHelper
) : MainDataSource {

    override suspend fun getProductInformation(): MainDto {
        return networkHelper.apiExecute {
            mainApiService.getProductInformation()
        }
    }
}