package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.interfaces.ResultListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilmModel {
    private lateinit var items: List<Film>

    fun getData(onResultListener: ResultListener) {
        GlobalScope.launch {
            items = DataSource.getFilmsFromXml()
        }
        if (items.isEmpty()) {
            onResultListener.onResultFail("Something happen during downloading list")
        } else {
            onResultListener.onResultSuccess(items)
        }
    }
}
