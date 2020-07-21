package com.example.stage2task5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.stage2task5.MainActivity
import com.example.stage2task5.R
import com.example.stage2task5.data.Cat

class CatAdapter : RecyclerView.Adapter<CatViewHolder>() {

    private val TAG = "Adapter"

    private val items = mutableListOf<Cat>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        val catViewHolder = CatViewHolder(view)
        val position = catViewHolder.adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            catViewHolder.imageView.setOnClickListener {
                //TODO third part of task
                Toast.makeText(parent.context, "open cat on flip full screen ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[position].imageUrl ?: ""
        holder.bind(imageUrl)
    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(imageUrl: String) {
        imageView.load(imageUrl)
    }
}
