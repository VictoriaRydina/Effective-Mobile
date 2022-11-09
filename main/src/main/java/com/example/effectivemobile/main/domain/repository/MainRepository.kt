package com.example.effectivemobile.main.domain.repository

import com.example.effectivemobile.main.domain.model.MainEntity

interface MainRepository {
    suspend fun getProductInformation() : MainEntity
}