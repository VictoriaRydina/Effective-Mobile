package com.example.effectivemobile.di.module

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppModule