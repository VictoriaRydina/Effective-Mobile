package com.example.effectivemobile.product_details.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.ui.HorizontalMarginItemDecoration
import com.example.effectivemobile.core_ui.utils.invisible
import com.example.effectivemobile.core_ui.utils.observe
import com.example.effectivemobile.product_details.*
import com.example.effectivemobile.product_details.databinding.FragmentDetailsProductBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.adapter.DetailsProductCarouselAdapter
import com.example.effectivemobile.product_details.presentation.adapter.ModelDescriptionAdapter
import com.example.effectivemobile.product_details.presentation.adapter.ModelDescriptionAdapter.Companion.DETAILS_FRAGMENT
import com.example.effectivemobile.product_details.presentation.adapter.ModelDescriptionAdapter.Companion.FEATURES_FRAGMENT
import com.example.effectivemobile.product_details.presentation.adapter.ModelDescriptionAdapter.Companion.SHOP_FRAGMENT
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.Float
import kotlin.math.abs
import kotlin.with

class DetailsProductFragment :
    BaseViewModelFragment<FragmentDetailsProductBinding, DetailsProductViewModel>(
        R.layout.fragment_details_product,
        FragmentDetailsProductBinding::inflate,
        DetailsProductViewModel::class.java
    ) {

    override fun initComponent() {
        DaggerDetailsProductComponent.factory()
            .create(requireActivity().application as DetailsProductDeps)
            .inject(this)
    }

    private val detailsProductCarouselAdapter = DetailsProductCarouselAdapter()

    override fun setUi() {
        super.setUi()
        initViewPager()
        viewModel.getProductDetails()
        viewModel.getCartProductList()
        with(binding) {
            detailsProductMagazineButton.setOnClickListener {
                navigateTo(R.id.cartFragment)
            }
            detailsProductBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        setObserve()
    }

    private fun setObserve() {
        with(binding) {
            observe(viewModel.cartProductLD) {
                if (it.basket.isEmpty()) {
                    numberOfItemsInTheCart.invisible()
                } else {
                    numberOfItemsInTheCart.text = it.basket.size.toString()
                }
            }
            observe(viewModel.productDetailsLD) {
                with(ratingDetails) {
                    titlePhoneName.text = it.title
                    ratingNumber.text = it.rating.toString()
                    detailsProductCarouselAdapter.submitList(it.images)
                }
            }
        }
    }

    private fun initViewPager() {
        with(binding) {
            // MyRecyclerViewAdapter is an standard RecyclerView.Adapter :)
            detailsProductPager.adapter = detailsProductCarouselAdapter
            // You need to retain one page on each side so that the next and previous items are visible
            detailsProductPager.offscreenPageLimit = 1
            // Add a PageTransformer that translates the next and previous items horizontally
            // towards the center of the screen, which makes them visible
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                // Next line scales the item's height. You can remove it if you don't want this effect
                page.scaleY = 1 - (0.25f * abs(position))
                // If you want a fading effect uncomment the next line:
                //page.alpha = 0.25f + (1 - abs(position))
            }
            detailsProductPager.setPageTransformer(pageTransformer)
            // The ItemDecoration gives the current (centered) item horizontal margin so that
            // it doesn't occupy the whole screen width. Without it the items overlap
            val itemDecoration = HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin
            )
            detailsProductPager.addItemDecoration(itemDecoration)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ModelDescriptionAdapter(childFragmentManager, lifecycle)
        with(binding) {
            modelDescriptionPager.adapter = adapter
            TabLayoutMediator(detailsProductTabLayout, modelDescriptionPager) { tab, position ->
                when (position) {
                    SHOP_FRAGMENT -> tab.text = getString(R.string.shop)
                    DETAILS_FRAGMENT -> tab.text = getString(R.string.details)
                    FEATURES_FRAGMENT -> tab.text = getString(R.string.features)
                }
            }.attach()
        }
    }
}