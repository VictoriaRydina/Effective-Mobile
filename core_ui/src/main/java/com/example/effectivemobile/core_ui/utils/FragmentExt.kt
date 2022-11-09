package com.example.effectivemobile.core_ui.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showToast(@StringRes textResId: Int) {
    Toast.makeText(context, textResId, Toast.LENGTH_SHORT)
        .show()
}

fun Fragment.showToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT)
        .show()
}