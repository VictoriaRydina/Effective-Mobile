package com.example.effectivemobile.product_details.presentation.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobile.core_ui.presentation.adapter.BaseViewPagerAdapter
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.ui.HorizontalMarginItemDecoration
import com.example.effectivemobile.core_ui.utils.invisible
import com.example.effectivemobile.core_ui.utils.observe
import com.example.effectivemobile.product_details.*
import com.example.effectivemobile.product_details.databinding.FragmentDetailsProductBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.adapter.DetailsProductCarouselAdapter
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.DetailsFragment
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.FeaturesFragment
import com.example.effectivemobile.product_details.presentation.fragment.model_description_fragments.ShopFragment
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
        setUpPageTransformerAndItemDecorator()
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
        initModelDescriptionPager()
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

    private fun setUpPageTransformerAndItemDecorator() {
        with(binding) {
            detailsProductPager.adapter = detailsProductCarouselAdapter
            detailsProductPager.offscreenPageLimit = 1
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * abs(position))
            }
            detailsProductPager.setPageTransformer(pageTransformer)
            val itemDecoration = HorizontalMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_current_item_horizontal_margin
            )
            detailsProductPager.addItemDecoration(itemDecoration)
        }
    }

    private fun initModelDescriptionPager() {
        val items = listOf(
            ShopFragment(),
            DetailsFragment(),
            FeaturesFragment()
        )
        val adapter = BaseViewPagerAdapter(childFragmentManager, lifecycle, items)
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

    companion object {
        private const val SHOP_FRAGMENT = 0
        private const val DETAILS_FRAGMENT = 1
        private const val FEATURES_FRAGMENT = 2
    }
}