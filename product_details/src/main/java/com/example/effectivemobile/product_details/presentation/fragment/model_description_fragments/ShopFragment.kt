package com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments

import android.view.View
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.core.graphics.toColorInt
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.utils.showToast
import com.example.effectivemobile.network.base.error.ErrorEntity
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentShopBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import com.example.effectivemobile.product_details.presentation.viewmodel.ViewState

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
        viewModel.setIdleState()
        viewModel.getProductDetails()
        selectModelCapacity()
    }

    override fun setObservers() {
        super.setObservers()
        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: ViewState) {
        when (state) {
            is ViewState.ProductDetailsState -> {
                val productDetails = state.data
                with(binding) {
                    cpuValue.text = productDetails.CPU
                    cameraValue.text = productDetails.camera
                    ssdValue.text = productDetails.ssd
                    sdValue.text = productDetails.sd
                    leftColorButton.setBackgroundColor(productDetails.color[0].toColorInt())
                    rightColorButton.setBackgroundColor(productDetails.color[1].toColorInt())
                    appCompatToggleButton.textOn = productDetails.capacity[0] + POSTFIX
                    appCompatToggleButton.textOff = productDetails.capacity[0] + POSTFIX
                    appCompatToggleButton2.textOn = productDetails.capacity[1] + POSTFIX
                    appCompatToggleButton2.textOff = productDetails.capacity[1] + POSTFIX
                    buyButton.text = PREFIX + productDetails.price.toString()
                }
            }
            is ViewState.ErrorState -> {
                when (state.data) {
                    is ErrorEntity.NoConnectionError -> {
                        showToast(state.data.localizedMessage ?: "No Connection Error")
                    }
                    is ErrorEntity.InternalServerError -> {
                        showToast(state.data.localizedMessage ?: "Internal Server Error")
                    }
                    else -> showToast(state.data.localizedMessage ?: "Network exception")
                }
            }
            else -> Unit
        }
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

    companion object {
        private const val POSTFIX = "gb"
        private const val PREFIX = "Add to card    $"
    }
}