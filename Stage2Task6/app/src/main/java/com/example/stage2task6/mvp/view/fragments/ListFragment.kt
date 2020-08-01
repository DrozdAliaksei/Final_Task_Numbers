package com.example.stage2task6.mvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stage2task6.R
import com.example.stage2task6.adapter.FilmAdapter
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.interfaces.ListView
import com.example.stage2task6.mvp.model.FilmViewModel
import com.example.stage2task6.mvp.presenters.ListPresenter
import kotlinx.android.synthetic.main.list_of_films_fragment.recyclerView

class ListFragment : Fragment() {

    private val filmAdapter = FilmAdapter()
    private lateinit var listPresenter: ListPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listPresenter = ListPresenter(ListViewImpl(), FilmViewModel())
        return inflater.inflate(R.layout.list_of_films_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    inner class ListViewImpl : ListView {

        override fun setData(newData: List<Film>) {
            filmAdapter.setList(newData)
        }

        override fun setDataError(strError: String) {
            Toast.makeText(context, strError, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        listPresenter.getData()
        super.onResume()
    }

    override fun onDestroyView() {
        listPresenter.onDestroy()
        super.onDestroyView()
    }
}
