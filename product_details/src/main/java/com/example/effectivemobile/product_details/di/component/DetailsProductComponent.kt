package com.example.effectivemobile.product_details.di.component

import com.example.effectivemobile.core_ui.di.scope.ModuleScope
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.FeaturesFragment
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.ShopFragment
import com.example.effectivemobile.product_details.presentation.fragment.CartFragment
import com.example.effectivemobile.product_details.presentation.fragment.DetailsProductFragment
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.DetailsFragment
import dagger.Component

@ModuleScope
@Component(
    dependencies = [DetailsProductDeps::class]
)
interface DetailsProductComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: DetailsProductDeps): DetailsProductComponent
    }

    fun inject(fragment: DetailsProductFragment)

    fun inject(fragment: DetailsFragment)

    fun inject(fragment: FeaturesFragment)

    fun inject(fragment: ShopFragment)

    fun inject(fragment: CartFragment)
}