package com.example.effectivemobile.core_ui.presentation.navigation

import androidx.annotation.StringRes
import com.example.effectivemobile.core_ui.R

enum class InternalDeepLink(@StringRes val resId: Int){
    MAIN(R.string.main_screen_deepLink),
    PRODUCT_DETAILS(R.string.product_details_deepLink),
    SHOP(R.string.shop_deepLink)
}