package com.example.stage2task6.mvp.presenters

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
            val result = filmModel?.getData()
            if (result != null) {
                if (result.isSuccess()) {
                    listView?.setData(result.data())
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
