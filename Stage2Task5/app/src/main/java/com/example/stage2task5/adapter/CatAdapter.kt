package com.example.stage2task5.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.stage2task5.R
import com.example.stage2task5.data.datasource.api.local.model.Cat

class CatAdapter : RecyclerView.Adapter<CatViewHolder>() {

    private val TAG = "Adapter"

    private var items = mutableListOf<Cat>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        val catViewHolder = CatViewHolder(view)
        val position = catViewHolder.adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            catViewHolder.imageView.setOnClickListener {
                //TODO third part of task
                Toast.makeText(parent.context, "open cat on flip full screen: ${catViewHolder.adapterPosition} ", Toast.LENGTH_SHORT)
                    .show()
                Log.e(TAG,"Where is my Toast ${catViewHolder.adapterPosition}")
            }
        }
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[holder.adapterPosition].imageUrl ?: ""
        holder.bind(imageUrl)
    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
//        items = newItems as MutableList<Cat>
//        notifyDataSetChanged()
        notifyItemRangeInserted(itemCount+1,10)
    }

    fun setData(newItems: List<Cat>) {
        val diffCallback = CatDiffCallback(items,newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageView: ImageView = view.findViewById(R.id.imageView)

    fun bind(imageUrl: String) {
        imageView.load(imageUrl)
    }
}
