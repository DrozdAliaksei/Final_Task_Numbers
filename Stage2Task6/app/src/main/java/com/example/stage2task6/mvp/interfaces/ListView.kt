package com.example.stage2task6.mvp.interfaces

import com.example.stage2task6.data.local.model.Film

interface ListView {
    fun setData(newData: List<Film>)
    fun setDataError(strError: String)
}
