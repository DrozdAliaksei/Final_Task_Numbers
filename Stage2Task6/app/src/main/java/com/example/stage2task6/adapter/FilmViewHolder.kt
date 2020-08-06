package com.example.stage2task6.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.stage2task6.data.local.model.Film
import kotlinx.android.synthetic.main.film_info_item.view.film_duration
import kotlinx.android.synthetic.main.film_info_item.view.film_preview
import kotlinx.android.synthetic.main.film_info_item.view.film_title

class FilmViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(film: Film) {
        view.film_preview.load(film.previewImageUrl)
        view.film_duration.text = film.duration
        view.film_title.text = film.title
    }
}
