package com.example.effectivemobile

import android.app.Application
import com.example.effectivemobile.di.component.AppComponent
import com.example.effectivemobile.di.component.DaggerAppComponent
import com.example.effectivemobile.di.deps.AppDeps

class App: Application(), AppDeps {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}