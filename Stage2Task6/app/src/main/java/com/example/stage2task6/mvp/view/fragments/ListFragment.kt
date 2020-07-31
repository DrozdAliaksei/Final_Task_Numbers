package com.example.stage2task6.mvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stage2task6.mvp.model.FilmViewModel
import com.example.stage2task6.R
import com.example.stage2task6.adapter.FilmAdapter
import kotlinx.android.synthetic.main.list_of_films_fragment.recyclerView

class ListFragment : Fragment() {

    private val filmViewModel by viewModels<FilmViewModel>()
    private val filmAdapter = FilmAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_of_films_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(context)
        }

        filmViewModel.items.observe(viewLifecycleOwner, Observer {
            it?: return@Observer
            filmAdapter.setList(it)
//            filmAdapter.notifyDataSetChanged()
        })
    }
}
