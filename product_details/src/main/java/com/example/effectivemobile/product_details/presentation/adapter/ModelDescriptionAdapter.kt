package com.example.effectivemobile.product_details.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ModelDescriptionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val items = listOf(
        ShopFragment(),
        DetailsFragment(),
        FeaturesFragment()
    )

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

    companion object {
        const val SHOP_FRAGMENT = 0
        const val DETAILS_FRAGMENT = 1
        const val FEATURES_FRAGMENT = 2
    }
}