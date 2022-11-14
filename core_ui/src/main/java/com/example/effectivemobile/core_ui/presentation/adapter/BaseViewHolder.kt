package com.example.effectivemobile.core_ui.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder <T>(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bindData(data: T)
}