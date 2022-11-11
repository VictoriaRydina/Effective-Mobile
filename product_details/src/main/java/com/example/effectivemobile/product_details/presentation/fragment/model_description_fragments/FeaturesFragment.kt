package com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments

import com.example.effectivemobile.core_ui.presentation.fragment.BaseFragment
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentFeaturesBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps

class FeaturesFragment : BaseFragment<FragmentFeaturesBinding>(
    R.layout.fragment_features,
    FragmentFeaturesBinding::inflate
) {

    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }
}