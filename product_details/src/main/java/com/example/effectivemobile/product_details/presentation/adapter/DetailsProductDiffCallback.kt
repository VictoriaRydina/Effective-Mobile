package com.example.effectivemobile.product_details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

class DetailsProductDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ) =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ) =
        oldItem == newItem
}