package com.example.effectivemobile.product_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.product_details.DetailsProductItemEntity
import com.example.effectivemobile.product_details.databinding.ItemCarouselBinding
import com.squareup.picasso.Picasso

class DetailsProductCarouselAdapter :
    ListAdapter<DetailsProductItemEntity, DetailsProductCarouselAdapter.CarouselItemViewHolder>(
        DetailsProductDiffCallback()
    ) {

    class CarouselItemViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailsProductItemEntity) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val binding = ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}