package com.example.effectivemobile.main

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.children
import com.example.effectivemobile.core_ui.BaseFragment
import com.example.effectivemobile.main.databinding.FragmentMainBinding
import com.example.effectivemobile.main.di.component.DaggerMainComponent
import com.example.effectivemobile.main.di.deps.MainDeps

class MainFragment : BaseFragment<FragmentMainBinding>(
    R.layout.fragment_main,
    FragmentMainBinding::inflate
) {

    override fun initComponent() {
        DaggerMainComponent.factory()
            .create(requireActivity().application as MainDeps)
            .inject(this)
    }

    override fun setUi() {
        super.setUi()
        setUpCategory()
        setUpTitle()
        with(binding) {
            categoryGroup.children.forEach { child ->
                child.setOnClickListener {
                    clearChecking(child)
                }
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

    private fun setUpTitle(){
        with(binding){
            titleHotSales.titleMain.setText(R.string.hot_sales)
            titleHotSales.buttonView.setText(R.string.see_more)
            titleBestSeller.titleMain.setText(R.string.best_seller)
            titleBestSeller.buttonView.setText(R.string.see_more)
        }
    }

    private fun setUpCategory(){
        with(binding){
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
}