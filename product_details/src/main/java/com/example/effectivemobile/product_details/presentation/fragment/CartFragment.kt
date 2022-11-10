package com.example.effectivemobile.product_details.presentation.fragment

import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.core_ui.presentation.fragment.BaseFragment
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentCartBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps

class CartFragment : BaseFragment<FragmentCartBinding>(
    R.layout.fragment_cart,
    FragmentCartBinding::inflate
) {
    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }

    override fun setUi() {
        super.setUi()
        binding.cartBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}