package com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments

import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.utils.observe
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentShopBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel

class ShopFragment : BaseViewModelFragment<FragmentShopBinding, DetailsProductViewModel>(
    R.layout.fragment_shop,
    FragmentShopBinding::inflate,
    DetailsProductViewModel::class.java
) {

    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }

    override fun setUi() {
        super.setUi()
        viewModel.getProductDetails()
        observe(viewModel.productDetailsLD) {
            with(binding) {
                textView3.text = it.CPU
                textView4.text = it.camera
                textView5.text = it.ssd
                textView6.text = it.sd
                appCompatToggleButton.textOn = it.capacity[0] + "gb"
                appCompatToggleButton.textOff = it.capacity[0] + "gb"
                appCompatToggleButton2.textOn = it.capacity[1] + "gb"
                appCompatToggleButton2.textOff = it.capacity[1] + "gb"
                button3.text = "Add to card    $" + it.price.toString()
            }
        }
    }
}