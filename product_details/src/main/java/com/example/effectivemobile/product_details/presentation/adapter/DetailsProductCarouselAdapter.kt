package com.example.effectivemobile.product_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.core_ui.presentation.adapter.BaseItemCallback
import com.example.effectivemobile.product_details.databinding.ItemCarouselBinding
import com.squareup.picasso.Picasso

class DetailsProductCarouselAdapter :
    ListAdapter<String, DetailsProductCarouselAdapter.CarouselItemViewHolder>(
        BaseItemCallback<String>()
    ) {

    class CarouselItemViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding){
                Picasso.get().load(item).into(detailsPicture)
            }
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