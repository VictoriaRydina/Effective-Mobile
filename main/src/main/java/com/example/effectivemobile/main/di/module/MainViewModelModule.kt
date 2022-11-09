package com.example.effectivemobile.main.di.module

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.core_ui.presentation.viewmodel.VMKey
import com.example.effectivemobile.main.data.api.MainApiService
import com.example.effectivemobile.main.data.repository.MainRepositoryImpl
import com.example.effectivemobile.main.data.source.MainDataSource
import com.example.effectivemobile.main.data.source.MainDataSourceImpl
import com.example.effectivemobile.main.domain.repository.MainRepository
import com.example.effectivemobile.main.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface MainViewModelModule {

    @[Binds IntoMap VMKey(MainViewModel::class)]
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    fun bindMainDataSource(impl: MainDataSourceImpl): MainDataSource

    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

    companion object{
        @Provides
        fun provideMainApiService(
            retrofit: Retrofit
        ): MainApiService = retrofit.create(MainApiService::class.java)
    }
}