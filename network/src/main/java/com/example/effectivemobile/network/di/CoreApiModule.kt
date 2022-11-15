package com.example.effectivemobile.network.di

import android.content.Context
import com.example.effectivemobile.network.base.NetworkHelper
import com.example.effectivemobile.network.base.NetworkHelperImpl
import com.example.effectivemobile.network.base.error.ErrorHelper
import com.example.effectivemobile.network.base.error.ErrorHelperImpl
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class CoreApiModule() {
    @Provides
    fun provideNetworkHelper(errorHelper: ErrorHelper): NetworkHelper =
        NetworkHelperImpl(errorHelper)

    @Provides
    fun provideErrorHelper(context: Context): ErrorHelper = ErrorHelperImpl(context)
}