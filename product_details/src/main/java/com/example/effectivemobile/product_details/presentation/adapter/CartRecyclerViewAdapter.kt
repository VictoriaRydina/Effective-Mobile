package com.example.effectivemobile.product_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.core_ui.presentation.adapter.BaseItemCallback
import com.example.effectivemobile.product_details.databinding.ItemCartPositionBinding
import com.example.effectivemobile.product_details.domain.model.BasketItemEntity
import com.squareup.picasso.Picasso

class CartRecyclerViewAdapter :
    ListAdapter<BasketItemEntity, CartRecyclerViewAdapter.CartItemViewHolder>(
        BaseItemCallback<BasketItemEntity>()
    ) {

    class CartItemViewHolder(private val binding: ItemCartPositionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BasketItemEntity) {
            binding.apply {
                Picasso.get().load(item.images).into(cartPhoneImage)
                cartItemTitle.text = item.title
                cartItemPrice.text = "$" + item.price
            }
        }
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = ItemCartPositionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartItemViewHolder(binding)
    }
}