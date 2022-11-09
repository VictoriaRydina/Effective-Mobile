package com.example.effectivemobile.main.di.deps

import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit

interface MainDeps {

    val mainRetrofit: Retrofit

    val mainViewModelFactory: ViewModelProvider.Factory
}