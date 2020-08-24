package com.example.finaltask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltask.R
import com.example.finaltask.data.local.model.Number

class NumberAdapter: RecyclerView.Adapter<NumberViewHolder>() {
    private var items = listOf<Number>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_list_item, null)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(items: List<Number>) {
        this.items = items
        notifyDataSetChanged()
    }
}
