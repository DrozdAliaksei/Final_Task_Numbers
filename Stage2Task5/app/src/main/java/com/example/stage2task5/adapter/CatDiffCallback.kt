package com.example.stage2task5.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stage2task5.data.datasource.api.local.model.Cat

class CatDiffCallback : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }
}
