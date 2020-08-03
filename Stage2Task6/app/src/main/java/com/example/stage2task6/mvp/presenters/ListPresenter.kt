package com.example.stage2task6.mvp.presenters

import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.model.FilmModel
import com.example.stage2task6.mvp.view.fragments.ListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListPresenter(
    private var listView: ListFragment.ListViewImpl?,
    private var filmModel: FilmModel?
) {
    fun getData() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = filmModel?.getData()
            if (response != null) {
                if (response.isSuccessful) {
                    listView?.setData(
                        response.body()
                            ?.channel
                            ?.itemsList?.map { item ->
                                Film(
                                    item.title,
                                    item.image?.url,
                                    item.duration,
                                    item.media?.content?.map { it.mediaUrl },
                                    item.description
                                )
                            }!!
                    )
                } else {
                    listView?.setDataError("Something happen during downloading data")
                }
            }
        }
    }

    fun onDestroy() {
        listView = null
    }
}
