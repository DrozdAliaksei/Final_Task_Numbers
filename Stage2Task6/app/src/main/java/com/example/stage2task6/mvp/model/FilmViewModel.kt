package com.example.stage2task6.mvp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.mvp.interfaces.ResultListener
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Film>>()
    val items: LiveData<List<Film>> get() = _items

    init {
        viewModelScope.launch {
            _items.value = DataSource.getFilmsFromXml()
//            _items.value = DataSource.getFilmsFromJson()
        }
    }

    fun getData(onResultListener: ResultListener) {
        onResultListener.onResultSuccess(items.value!!)
    }
}
