package com.example.finaltask.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaltask.data.local.model.Number
import com.example.finaltask.data.repository.MyRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo: MyRepository
) : ViewModel() {
    private val _number = MutableLiveData<Number>()
    val number: LiveData<Number> get() = _number

    fun getTypedNumber(type: String) {
        viewModelScope.launch {
            _number.value = repo.getRandomNumberOfType(type)
        }
    }
}
