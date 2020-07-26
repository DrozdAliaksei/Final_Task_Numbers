package com.example.stage2task5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.stage2task5.R
import com.example.stage2task5.data.datasource.api.local.model.Cat

class CatAdapter(val onImageClickListner: (cat: Cat) -> Unit) :
    ListAdapter<Cat, CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        val catViewHolder = CatViewHolder(view)
        catViewHolder.imageView.setOnClickListener {
            onImageClickListner(getItem(catViewHolder.adapterPosition))
        }

        return catViewHolder
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        holder.bind(item)
    }
}

class CatViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(cat: Cat) {
        val imageUrl = cat.imageUrl
        imageView.load(imageUrl) {
            crossfade(true)
            transformations(
                CircleCropTransformation()
            )
        }
    }
}
