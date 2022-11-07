package com.example.effectivemobile.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.core_ui.ApplicationScope
import com.example.effectivemobile.core_ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @ApplicationScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}