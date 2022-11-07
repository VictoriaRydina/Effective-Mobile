package com.example.effectivemobile.di.module

import dagger.Module

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
interface AppModule