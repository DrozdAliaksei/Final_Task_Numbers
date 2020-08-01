package com.example.stage2task6.mvp.interfaces

import com.example.stage2task6.data.local.model.Film

interface ResultListener {
    fun onResultSuccess(newData: List<Film>)
    fun onResultFail(strError: String)
}
