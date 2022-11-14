package com.example.effectivemobile.core_ui.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}