package com.example.effectivemobile.di.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.di.module.AppModule
import com.example.effectivemobile.core_ui.di.scope.ApplicationScope
import com.example.effectivemobile.presentation.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ApplicationScope
@Component(
    modules = [
        AppModule::class
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun okHttpClient(): OkHttpClient

    fun retrofit(): Retrofit

    fun viewModelFactory(): ViewModelProvider.Factory

    fun inject(mainActivity: MainActivity)
}