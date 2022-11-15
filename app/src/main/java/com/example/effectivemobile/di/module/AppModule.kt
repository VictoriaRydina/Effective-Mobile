package com.example.effectivemobile.di.module

import com.example.effectivemobile.network.di.CoreApiModule
import dagger.Module

@Module(
    includes = [
        CoreApiModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppModule