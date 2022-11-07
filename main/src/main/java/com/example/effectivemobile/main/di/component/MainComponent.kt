package com.example.effectivemobile.main.di.component

import com.example.effectivemobile.core_ui.ModuleScope
import com.example.effectivemobile.main.MainFragment
import com.example.effectivemobile.main.di.deps.MainDeps
import dagger.Component

@ModuleScope
@Component(
    dependencies = [MainDeps::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: MainDeps): MainComponent
    }

    fun inject(fragment: MainFragment)
}