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

    private val _items = MutableLiveData<List<Cat>>()
    val items: LiveData<List<Cat>> get() = _items
    var page: Int = 10

    init {
        getPageOfCats()
    }

    fun getPageOfCats() {
        viewModelScope.launch {
            _items.value = CatDataSource.getPageOfCats(page)
            page++
            Log.e("CatModel", "cats responce rith page: $page")
        }
    }
}
