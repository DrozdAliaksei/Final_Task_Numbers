package com.example.finaltask.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.finaltask.data.local.model.Number
import kotlinx.android.synthetic.main.history_list_item.view.history_number
import kotlinx.android.synthetic.main.history_list_item.view.history_text

class NumberViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(number: Number) {
        view.history_number.text = number.name()
        view.history_text.text = number.text
    }
}
