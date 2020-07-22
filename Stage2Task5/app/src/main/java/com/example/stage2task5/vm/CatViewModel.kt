package com.example.stage2task5.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stage2task5.data.datasource.api.CatDataSource
import com.example.stage2task5.data.datasource.api.local.model.Cat
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    private val _items = MutableLiveData<MutableList<Cat>>()
    val items: LiveData<MutableList<Cat>> get() = _items
    private var page: Int = 10

    init {
        viewModelScope.launch {
            _items.value = CatDataSource.getPageOfCats(page).toMutableList()
            page++
        }
    }

    fun getPageOfCats() {
        viewModelScope.launch {
            val list = items.value
//            _items.postValue(CatDataSource.getPageOfCats(page).toMutableList())
            list?.addAll(CatDataSource.getPageOfCats(page))
            _items.value = list
            page++
            Log.e("CatModel", "cats responce rith page: $page")
        }
    }
}
