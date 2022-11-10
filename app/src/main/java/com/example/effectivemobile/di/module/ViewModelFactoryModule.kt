package com.example.effectivemobile.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.core_ui.di.scope.ApplicationScope
import com.example.effectivemobile.core_ui.presentation.viewmodel.ViewModelFactory
import com.example.effectivemobile.main.di.module.MainViewModelModule
import com.example.effectivemobile.product_details.di.module.DetailsProductViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        MainViewModelModule::class,
        DetailsProductViewModelModule::class
    ]
)
interface ViewModelFactoryModule {

    @ApplicationScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}