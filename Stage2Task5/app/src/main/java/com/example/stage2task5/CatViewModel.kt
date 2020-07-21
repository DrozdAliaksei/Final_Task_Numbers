package com.example.stage2task5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stage2task5.data.Cat
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Cat>>()
    val items: LiveData<List<Cat>> get() = _items
    var page: Int = 1

    init {
        viewModelScope.launch {
            _items.value = CatApiImpl.getPageOfCats(page)
            Log.e("CatModel", "cats page: $page")
        }
    }

    fun getPageOfCats() {
        viewModelScope.launch {
            page++
            _items.value = CatApiImpl.getPageOfCats(page)

            Log.e("CatModel", "cats responce rith page: $page")
        }
    }
}
