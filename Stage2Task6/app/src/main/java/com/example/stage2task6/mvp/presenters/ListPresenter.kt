package com.example.stage2task6.mvp.presenters

import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.interfaces.ResultListener
import com.example.stage2task6.mvp.model.FilmModel
import com.example.stage2task6.mvp.view.fragments.ListFragment

class ListPresenter(
    private var listView: ListFragment.ListViewImpl?,
    private var filmModel: FilmModel?
): ResultListener {
    fun getData() {
        filmModel?.getData(this)
    }

    override fun onResultSuccess(newData: List<Film>) {
        listView?.setData(newData)
    }

    override fun onResultFail(strError: String) {
        listView?.setDataError(strError)
    }

    fun onDestroy() {
        listView = null
    }
}
