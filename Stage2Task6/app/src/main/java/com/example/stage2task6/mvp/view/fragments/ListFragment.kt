package com.example.stage2task6.mvp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stage2task6.R
import com.example.stage2task6.adapter.FilmAdapter
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.view.ListView
import com.example.stage2task6.mvp.model.FilmModel
import com.example.stage2task6.mvp.presenters.ListPresenter
import kotlinx.android.synthetic.main.list_of_films_fragment.recyclerView
import kotlinx.android.synthetic.main.list_of_films_fragment.source_selector_menu

class ListFragment : Fragment() {

    private val filmAdapter = FilmAdapter{
        findNavController().navigate(R.id.FilmFragment, bundleOf("Film" to it))
    }
    private lateinit var listPresenter: ListPresenter
    private var source = RSS
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listPresenter = ListPresenter(ListViewImpl(), FilmModel())
        return inflater.inflate(R.layout.list_of_films_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(context)
        }
        listPresenter.getData(source)

        source_selector_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.xml_source -> {
                    source = RSS
                    listPresenter.getData(source)
                    Log.i(TAG, "RSS source")
                    true
                }
                R.id.json_source -> {
                    source = REPO
                    listPresenter.getData(source)
                    Log.i(TAG, "Repo source")
                    true
                }
                else -> false
            }
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

    override fun onDestroyView() {
        listPresenter.onDestroy()
        super.onDestroyView()
    }

    companion object {
        const val RSS = "Ted rss"
        const val REPO = "Repository"
        const val TAG: String = "ListFragment"
    }
}
