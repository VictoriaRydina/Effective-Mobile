package com.example.effectivemobile.main.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.core_ui.presentation.adapter.BaseItemCallback
import com.example.effectivemobile.main.R
import com.example.effectivemobile.main.databinding.ItemBestSellerBinding
import com.example.effectivemobile.main.domain.model.BestSellerItemEntity
import com.squareup.picasso.Picasso

class BestSellerAdapter(private val onItemClickListener: (BestSellerItemEntity) -> Unit) :
    ListAdapter<BestSellerItemEntity, BestSellerAdapter.BestSellerItemViewHolder>(
        BaseItemCallback<BestSellerItemEntity>()
    ) {

    class BestSellerItemViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: BestSellerItemEntity,
            onItemClickListener: (BestSellerItemEntity) -> Unit
        ) {
            binding.apply {
                Picasso.get().load(item.picture).into(phoneImage)
                phoneCost.text = PREFIX + item.price_without_discount.toString()
                discountPhonePrice.text = PREFIX + item.discount_price.toString()
                phoneName.text = item.title
                if (item.is_favorites) {
                    likeButton.setImageResource(R.drawable.painted_over_heart)
                } else likeButton.setImageResource(R.drawable.orange_heart)
                root.setOnClickListener { onItemClickListener(item) }
            }
        }
    }

    override fun onBindViewHolder(holder: BestSellerItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerItemViewHolder {
        val binding = ItemBestSellerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BestSellerItemViewHolder(binding)
    }

    companion object {
        private const val PREFIX = "$"
    }
}