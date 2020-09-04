package com.example.finaltask.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask.data.local.model.Number
import com.example.finaltask.data.repository.MyRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repo: MyRepository
) : ViewModel() {
    private val _list = MutableLiveData<List<Number>>()
    val list: LiveData<List<Number>> get() = _list

    init {
        viewModelScope.launch {
            _list.value = repo.getListOfSavedNumbers()
        }
    }

    fun cleanHistory() {
        repo.clearHistory()
        _list.value = listOf()
    }
}
