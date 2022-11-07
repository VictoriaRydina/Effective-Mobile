package com.example.effectivemobile.main

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
}