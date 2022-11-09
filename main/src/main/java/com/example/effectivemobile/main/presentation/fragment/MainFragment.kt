package com.example.effectivemobile.main.presentation.fragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.effectivemobile.core_ui.utils.invisible
import com.example.effectivemobile.core_ui.utils.observe
import com.example.effectivemobile.core_ui.utils.visible
import com.example.effectivemobile.main.R
import com.example.effectivemobile.main.databinding.FragmentMainBinding
import com.example.effectivemobile.main.di.component.DaggerMainComponent
import com.example.effectivemobile.main.di.deps.MainDeps
import com.example.effectivemobile.main.presentation.adapters.BestSellerAdapter
import com.example.effectivemobile.main.presentation.adapters.HotSalesAdapter
import com.example.effectivemobile.main.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainFragment : BaseViewModelFragment<FragmentMainBinding, MainViewModel>(
    R.layout.fragment_main,
    FragmentMainBinding::inflate,
    MainViewModel::class.java
) {

    private lateinit var hotSalesAdapter: HotSalesAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter

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
        viewModel.getProductInformation()
        with(binding) {
            categoryGroup.children.forEach { child ->
                child.setOnClickListener {
                    clearChecking(child)
                }
            }
            hotSalesAdapter = HotSalesAdapter()
            bestSellerAdapter = BestSellerAdapter()
            hotSalesRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hotSalesRecycler.adapter = hotSalesAdapter
            bestSellerRecycler.layoutManager =
                GridLayoutManager(context, 2)
            bestSellerRecycler.adapter = bestSellerAdapter
            observe(viewModel.mainLD) {
                hotSalesAdapter.submitList(it.home_store)
                bestSellerAdapter.submitList(it.best_seller)
            }
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
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.locatorBottomSheet)
        binding.mainFilter.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                binding.locatorBottomSheet.invisible()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                binding.locatorBottomSheet.visible()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}