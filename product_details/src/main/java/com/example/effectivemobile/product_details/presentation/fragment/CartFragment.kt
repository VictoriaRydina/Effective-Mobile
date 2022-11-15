package com.example.effectivemobile.product_details.presentation.fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.utils.showToast
import com.example.effectivemobile.network.base.error.ErrorEntity
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentCartBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.adapter.CartRecyclerViewAdapter
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import com.example.effectivemobile.product_details.presentation.viewmodel.ViewState

class CartFragment : BaseViewModelFragment<FragmentCartBinding, DetailsProductViewModel>(
    R.layout.fragment_cart,
    FragmentCartBinding::inflate,
    DetailsProductViewModel::class.java
) {
    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }

    private lateinit var cartRecyclerViewAdapter: CartRecyclerViewAdapter

    override fun setUi() {
        super.setUi()
        viewModel.setIdleState()
        viewModel.getCartProductList()
        with(binding) {
            cartBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
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
            is ViewState.CartProductState -> {
                val cartProduct = state.data
                with(binding) {
                    total.text = "$" + cartProduct.total.toString() + "us"
                    delivery.text = cartProduct.delivery
                    cartRecyclerViewAdapter = CartRecyclerViewAdapter()
                    cartRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    cartRecyclerView.adapter = cartRecyclerViewAdapter
                    cartRecyclerViewAdapter.submitList(cartProduct.basket)
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
}