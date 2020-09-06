package com.example.stage2task6.mvp.presenters

import com.example.stage2task6.mvp.model.FilmModel
import com.example.stage2task6.mvp.model.Result
import com.example.stage2task6.mvp.view.fragments.ListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListPresenter(
    private var listView: ListFragment.ListViewImpl?,
    private val filmModel: FilmModel
) {
    fun getData(source: String) {
        GlobalScope.launch(Dispatchers.Main) {
            when(val result = filmModel.getData(source)){
                is Result.ResultSuccess -> {
                    listView?.setData(result.data)
                }
                is Result.ResultFailed -> {
                    listView?.setDataError(result.message)
                }
            }
        }
    }

    fun onDestroy() {
        listView = null
    }
}
