package com.example.effectivemobile

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.di.component.AppComponent
import com.example.effectivemobile.di.component.DaggerAppComponent
import com.example.effectivemobile.di.deps.AppDeps
import retrofit2.Retrofit

class App: Application(), AppDeps {

    lateinit var appComponent: AppComponent

    override val mainViewModelFactory: ViewModelProvider.Factory
        get() = appComponent.viewModelFactory()

    override val mainRetrofit: Retrofit
        get() = appComponent.retrofit()

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}