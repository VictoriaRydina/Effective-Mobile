package com.example.effectivemobile.product_details.di.module

import androidx.lifecycle.ViewModel
import com.example.effectivemobile.core_ui.presentation.viewmodel.VMKey
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailsProductViewModelModule {

    @[Binds IntoMap VMKey(DetailsProductViewModel::class)]
    fun bindMainViewModel(viewModel: DetailsProductViewModel): ViewModel
}