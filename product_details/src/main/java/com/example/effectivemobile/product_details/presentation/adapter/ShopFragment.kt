package com.example.effectivemobile.product_details.presentation.adapter

import com.example.effectivemobile.core_ui.presentation.fragment.BaseFragment
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentShopBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps

class ShopFragment : BaseFragment<FragmentShopBinding>(
    R.layout.fragment_shop,
    FragmentShopBinding::inflate
) {

    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }
}