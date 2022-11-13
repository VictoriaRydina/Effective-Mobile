package com.example.effectivemobile.product_details.presentation.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.utils.observe
import com.example.effectivemobile.product_details.R
import com.example.effectivemobile.product_details.databinding.FragmentCartBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.adapter.CartRecyclerViewAdapter
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel

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
        viewModel.getCartProductList()
        with(binding) {
            cartBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
            observe(viewModel.cartProductLD) {
                total.text = "$" + it.total.toString() + "us"
                delivery.text = it.delivery
            }
            initRecyclersView()
        }
    }

    private fun initRecyclersView() {
        with(binding) {
            cartRecyclerViewAdapter = CartRecyclerViewAdapter()
            cartRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            cartRecyclerView.adapter = cartRecyclerViewAdapter
            observe(viewModel.cartProductLD) {
                cartRecyclerViewAdapter.submitList(it.basket)
            }
        }
    }
}