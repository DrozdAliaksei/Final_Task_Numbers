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
        Log.e(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        val catViewHolder = CatViewHolder(view)
        catViewHolder.imageView.setOnClickListener {
            onImageClickListner(getItem(catViewHolder.adapterPosition))
            Log.e(TAG, "Where is my Toast ${catViewHolder.adapterPosition}")
        }

        return catViewHolder
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder")
//        val item = getItem(position)
        val item = getItem(holder.adapterPosition)
        holder.bind(item)
    }
}

class CatViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(cat: Cat) {
        Log.e("Adapter", " method bind in viewHolder")
        val imageUrl = cat.imageUrl
        imageView.load(imageUrl) {
            crossfade(true)
            transformations(
                CircleCropTransformation()
            )
        }
    }
}
