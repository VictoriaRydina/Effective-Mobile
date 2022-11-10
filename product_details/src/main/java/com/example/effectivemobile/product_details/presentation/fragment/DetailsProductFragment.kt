package com.example.effectivemobile.product_details.presentation.fragment

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.ui.HorizontalMarginItemDecoration
import com.example.effectivemobile.product_details.*
import com.example.effectivemobile.product_details.databinding.FragmentDetailsProductBinding
import com.example.effectivemobile.product_details.di.component.DaggerDetailsProductComponent
import com.example.effectivemobile.product_details.di.deps.DetailsProductDeps
import com.example.effectivemobile.product_details.presentation.adapter.DetailsProductCarouselAdapter
import com.example.effectivemobile.product_details.presentation.viewmodel.DetailsProductViewModel
import kotlin.math.abs

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

    override fun setUi() {
        super.setUi()
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            // MyRecyclerViewAdapter is an standard RecyclerView.Adapter :)
            detailsProductPager.adapter = DetailsProductCarouselAdapter()
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
}