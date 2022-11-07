package com.example.effectivemobile.di.component

import android.content.Context
import com.example.effectivemobile.di.module.AppModule
import com.example.effectivemobile.core_ui.ApplicationScope
import com.example.effectivemobile.presentation.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

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

    fun inject(mainActivity: MainActivity)
}