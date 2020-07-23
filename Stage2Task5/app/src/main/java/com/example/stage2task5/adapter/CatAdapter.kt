package com.example.stage2task5.adapter

import android.util.Log
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

    private val TAG = "Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)

        return CatViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition).imageUrl ?: ""
        holder.bind(item)
    }
}

class CatViewHolder(view: View, private val adapter: CatAdapter) :
    RecyclerView.ViewHolder(view) {

    private val imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(true)
            transformations(
                CircleCropTransformation()
            )
        }
        imageView.setOnClickListener {
            adapter.onImageClickListner(adapter.currentList[this.adapterPosition])
            Log.e("Adapter", "onImageClick   ")
        }
    }
}
