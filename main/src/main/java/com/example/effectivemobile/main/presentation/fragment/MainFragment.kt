package com.example.effectivemobile.main.presentation.fragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.presentation.navigation.InternalDeepLink
import com.example.effectivemobile.core_ui.utils.*
import com.example.effectivemobile.main.R
import com.example.effectivemobile.main.databinding.FragmentMainBinding
import com.example.effectivemobile.main.di.component.DaggerMainComponent
import com.example.effectivemobile.main.di.deps.MainDeps
import com.example.effectivemobile.main.presentation.adapters.BestSellerAdapter
import com.example.effectivemobile.main.presentation.adapters.HotSalesAdapter
import com.example.effectivemobile.main.presentation.viewmodel.MainViewModel
import com.example.effectivemobile.main.presentation.viewmodel.ViewState
import com.example.effectivemobile.network.base.error.ErrorEntity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment : BaseViewModelFragment<FragmentMainBinding, MainViewModel>(
    R.layout.fragment_main,
    FragmentMainBinding::inflate,
    MainViewModel::class.java
) {

    private lateinit var hotSalesAdapter: HotSalesAdapter
    private var bestSellerAdapter = setUpBestSellerAdapter()

    override fun initComponent() {
        DaggerMainComponent.factory()
            .create(requireActivity().application as MainDeps)
            .inject(this)
    }

    override fun setUi() {
        super.setUi()
        setUpCategory()
        setUpTitle()
        setupBottomSheetController()
        viewModel.setIdleState()
        viewModel.getProductInformation()
        setUpSomeButton()
        with(binding) {
            categoryGroup.children.forEach { child ->
                child.setOnClickListener {
                    clearChecking(child)
                    child.isPressed = true
                }
            }
            bottomNavView.shopIcon.setOnClickListener {
                navigateTo(InternalDeepLink.SHOP)
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
            is ViewState.MainReceivedState -> {
                val bestSeller = state.data.best_seller
                val homeStore = state.data.home_store
                with(binding) {
                    hotSalesAdapter = HotSalesAdapter()
                    hotSalesRecycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    hotSalesRecycler.adapter = hotSalesAdapter
                    bestSellerRecycler.layoutManager =
                        GridLayoutManager(context, 2)
                    bestSellerRecycler.adapter = bestSellerAdapter
                    hotSalesAdapter.submitList(homeStore)
                    bestSellerAdapter.submitList(bestSeller)
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

    private fun setUpBestSellerAdapter(): BestSellerAdapter {
        return BestSellerAdapter {
            navigateTo(InternalDeepLink.PRODUCT_DETAILS)
        }
    }

    private fun clearChecking(currentChild: View) {
        binding.apply {
            categoryGroup.children.forEach { child ->
                if (child != currentChild)
                    (child as AppCompatImageButton).isPressed = false
            }
        }
    }

    private fun setUpSomeButton() {
        with(binding) {
            selectCategoryTitle.buttonView.setOnClickListener {
                showToast("Navigate to see all categories")
            }
            titleHotSales.buttonView.setOnClickListener {
                showToast("Navigate to see all Hot sales")
            }
            titleBestSeller.buttonView.setOnClickListener {
                showToast("Navigate to see all Best sellers")
            }
            searchLayout.qrCodeButton.setOnClickListener {
                showToast("Scan barcode")
            }
            bottomNavView.profileIcon.setOnClickListener {
                showToast("Navigate to profile")
            }
            bottomNavView.favoritesIcon.setOnClickListener {
                showToast("Navigate to favorites")
            }
        }
    }

    private fun setUpTitle() {
        with(binding) {
            titleHotSales.titleMain.setText(R.string.hot_sales)
            titleHotSales.buttonView.setText(R.string.see_more)
            titleBestSeller.titleMain.setText(R.string.best_seller)
            titleBestSeller.buttonView.setText(R.string.see_more)
        }
    }

    private fun setUpCategory() {
        with(binding) {
            computer.categoryTitle.setText(R.string.computer)
            health.categoryTitle.setText(R.string.health)
            books.categoryTitle.setText(R.string.books)
            computerTwo.categoryTitle.setText(R.string.computer)
            healthTwo.categoryTitle.setText(R.string.health)

            computer.categoryIcon.setImageResource(R.drawable.ic_computer)
            health.categoryIcon.setImageResource(R.drawable.ic_health)
            books.categoryIcon.setImageResource(R.drawable.ic_books)
            computerTwo.categoryIcon.setImageResource(R.drawable.ic_computer)
            healthTwo.categoryIcon.setImageResource(R.drawable.ic_health)
        }
    }

    private fun setupBottomSheetController() {
        with(binding) {
            val bottomSheetBehavior = BottomSheetBehavior.from(mainBottomSheet)
            mainFilter.setOnClickListener {
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    mainBottomSheet.invisible()
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                } else {
                    mainBottomSheet.visible()
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }
}