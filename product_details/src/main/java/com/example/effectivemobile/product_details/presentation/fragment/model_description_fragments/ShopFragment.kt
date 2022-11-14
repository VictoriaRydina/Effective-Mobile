package com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments

import android.view.View
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.core.graphics.toColorInt
import androidx.core.view.children
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
                cpuValue.text = it.CPU
                cameraValue.text = it.camera
                ssdValue.text = it.ssd
                sdValue.text = it.sd
                leftColorButton.setBackgroundColor(it.color[0].toColorInt())
                rightColorButton.setBackgroundColor(it.color[1].toColorInt())
                appCompatToggleButton.textOn = it.capacity[0] + POSTFIX
                appCompatToggleButton.textOff = it.capacity[0] + POSTFIX
                appCompatToggleButton2.textOn = it.capacity[1] + POSTFIX
                appCompatToggleButton2.textOff = it.capacity[1] + POSTFIX
                buyButton.text = PREFIX + it.price.toString()
            }
        }
        selectModelCapacity()
    }

    private fun selectModelCapacity() {
        with(binding) {
            capacity.children.forEach { child ->
                child.setOnClickListener {
                    clearChecking(child)
                }
            }
        }
    }

    private fun clearChecking(currentChild: View) {
        binding.apply {
            capacity.children.forEach { child ->
                if (child != currentChild)
                    (child as AppCompatToggleButton).isChecked = false
            }
        }
    }

    companion object{
        private const val POSTFIX = "gb"
        private const val PREFIX = "Add to card    $"
    }
}