package com.example.effectivemobile.main.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.main.R
import com.example.effectivemobile.main.databinding.ItemBestSellerBinding
import com.example.effectivemobile.main.domain.model.BestSellerItemEntity
import com.squareup.picasso.Picasso

class BestSellerAdapter :
    ListAdapter<BestSellerItemEntity, BestSellerAdapter.BestSellerItemViewHolder>(
        BestSellerDiffCallback()
    ) {

    class BestSellerItemViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BestSellerItemEntity) {
            binding.apply {
                Picasso.get().load(item.picture).into(phoneImage)
                phoneCost.text = PREFIX + item.price_without_discount.toString()
                discountPhonePrice.text = PREFIX + item.discount_price.toString()
                phoneName.text = item.title
                if(item.is_favorites){
                    likeButton.setImageResource(R.drawable.painted_over_heart)
                } else likeButton.setImageResource(R.drawable.orange_heart)
            }
        }
    }

    override fun onBindViewHolder(holder: BestSellerItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerItemViewHolder {
        val binding = ItemBestSellerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BestSellerItemViewHolder(binding)
    }

    companion object{
        private const val PREFIX = "$"
    }
}

class BestSellerDiffCallback : DiffUtil.ItemCallback<BestSellerItemEntity>() {

    override fun areItemsTheSame(oldItem: BestSellerItemEntity, newItem: BestSellerItemEntity) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BestSellerItemEntity, newItem: BestSellerItemEntity) =
        oldItem == newItem
}