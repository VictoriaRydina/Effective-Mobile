package com.example.effectivemobile.main.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.core_ui.presentation.adapter.BaseItemCallback
import com.example.effectivemobile.core_ui.utils.visible
import com.example.effectivemobile.main.databinding.ItemHotSalesBinding
import com.example.effectivemobile.main.domain.model.HotSalesItemEntity
import com.squareup.picasso.Picasso

class HotSalesAdapter :
    ListAdapter<HotSalesItemEntity, HotSalesAdapter.HotSalesItemViewHolder>(
        BaseItemCallback<HotSalesItemEntity>()
    ) {

    class HotSalesItemViewHolder(private val binding: ItemHotSalesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotSalesItemEntity) {
            binding.apply {
                if (item.is_new) iconNew.visible()
                titlePhoneName.text = item.title
                subtitle.text = item.subtitle
                Picasso.get().load(item.picture).into(phonePicture)
            }
        }
    }

    override fun onBindViewHolder(holder: HotSalesItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesItemViewHolder {
        val binding = ItemHotSalesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotSalesItemViewHolder(binding)
    }
}