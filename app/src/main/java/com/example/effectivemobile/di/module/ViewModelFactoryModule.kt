package com.example.effectivemobile.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.core_ui.ApplicationScope
import com.example.effectivemobile.core_ui.ViewModelFactory
import com.example.effectivemobile.main.di.module.MainViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        MainViewModelModule::class
    ]
)
interface ViewModelFactoryModule {

    @ApplicationScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}