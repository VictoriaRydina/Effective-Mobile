package com.example.effectivemobile.main.domain.use_case

import com.example.effectivemobile.main.domain.model.MainEntity
import com.example.effectivemobile.main.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase
@Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun getMainModel() : MainEntity {
        return mainRepository.getProductInformation()
    }
}