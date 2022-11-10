package com.example.effectivemobile.product_details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.effectivemobile.product_details.DetailsProductItemEntity

class DetailsProductDiffCallback : DiffUtil.ItemCallback<DetailsProductItemEntity>() {

    override fun areItemsTheSame(
        oldItem: DetailsProductItemEntity,
        newItem: DetailsProductItemEntity
    ) =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: DetailsProductItemEntity,
        newItem: DetailsProductItemEntity
    ) =
        oldItem == newItem
}