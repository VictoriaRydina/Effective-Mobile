package com.example.effectivemobile.main.data.repository

import com.example.effectivemobile.main.data.mapper.MainMapper
import com.example.effectivemobile.main.data.source.MainDataSource
import com.example.effectivemobile.main.domain.model.MainEntity
import com.example.effectivemobile.main.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(
    private val mainDataSource: MainDataSource,
    private val mainMapper: MainMapper
) : MainRepository {

    override suspend fun getProductInformation(): MainEntity {
        return mainDataSource.getProductInformation().let {
            mainMapper.map(it)
        }
    }
}