package com.example.effectivemobile.core_ui.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.effectivemobile.core_ui.presentation.navigation.InternalDeepLink
import com.example.effectivemobile.core_ui.R

abstract class BaseFragment<VB : ViewBinding>(
    @LayoutRes private val layoutRes: Int,
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment(layoutRes) {

    private var _binding: VB? = null
    val binding get() = requireNotNull(_binding)

    override fun onAttach(context: Context) {
        initComponent()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()
    }

    open fun navigateTo(
        deepLink: InternalDeepLink,
        navOptions: NavOptions? = getNavOptions(),
        navigatorExtras: Navigator.Extras? = null
    ) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(requireContext().getString(deepLink.resId).toUri())
            .build()
        findNavController().navigate(request, navOptions, navigatorExtras)
    }

    open fun navigateTo(
        fragmentId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = getNavOptions()
    ) {
        findNavController().navigate(fragmentId, args, navOptions)
    }

    private fun getNavOptions(): NavOptions {
        return NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setExitAnim(R.anim.to_left_out)
            .setEnterAnim(R.anim.to_left_in)
            .setPopExitAnim(R.anim.to_right_out)
            .build()
    }

    protected open fun setUi() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Init dagger component here
     * */
    abstract fun initComponent()
}