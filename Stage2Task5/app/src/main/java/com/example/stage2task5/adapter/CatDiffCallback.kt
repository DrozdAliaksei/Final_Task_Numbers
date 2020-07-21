package com.example.stage2task5.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stage2task5.data.datasource.api.local.model.Cat

class CatDiffCallback(private val oldList: List<Cat>, private val newList:List<Cat>):
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imageUrl === newList[newItemPosition].imageUrl
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}