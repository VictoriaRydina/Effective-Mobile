package com.example.effectivemobile.product_details.di.module

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.core_ui.presentation.viewmodel.VMKey
import com.example.effectivemobile.product_details.data.api.ProductDetailsService
import com.example.effectivemobile.product_details.data.datasource.ProductDetailsDataSource
import com.example.effectivemobile.product_details.data.datasource.ProductDetailsDataSourceImpl
import com.example.effectivemobile.product_details.data.repository.ProductDetailsRepositoryImpl
import com.example.effectivemobile.product_details.domain.repository.ProductDetailsRepository
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
interface DetailsProductViewModelModule {

    @[Binds IntoMap VMKey(DetailsProductViewModel::class)]
    fun bindMainViewModel(viewModel: DetailsProductViewModel): ViewModel

    @Binds
    fun bindMainDataSource(impl: ProductDetailsDataSourceImpl): ProductDetailsDataSource

    @Binds
    fun bindMainRepository(impl: ProductDetailsRepositoryImpl): ProductDetailsRepository

    companion object {
        @Provides
        fun provideMainApiService(
            retrofit: Retrofit
        ): ProductDetailsService = retrofit.create(ProductDetailsService::class.java)
    }
}