package com.example.effectivemobile.main.data.source

import com.example.effectivemobile.main.data.model.MainDto

interface MainDataSource {

    suspend fun getProductInformation() : MainDto
}