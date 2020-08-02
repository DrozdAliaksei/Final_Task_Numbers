package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.interfaces.ResultListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilmViewModel {
    private var items = listOf<Film>()

    init {
        GlobalScope.launch {
            items = DataSource.getFilmsFromXml()
        }
    }

    fun getData(onResultListener: ResultListener) {
        onResultListener.onResultSuccess(items)
    }
}
