package com.example.stage2task5.vm

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import coil.request.LoadRequest
import com.example.stage2task5.data.datasource.api.local.model.Cat

class CatImageViewModel : ViewModel() {
    private val _catData = MutableLiveData<Bitmap>()
    val catdata: LiveData<Bitmap> get() = _catData
    private lateinit var cat: Cat

    fun saveCat(arguments: Bundle?) {
        cat = arguments?.getParcelable("Cat")!!
    }

    fun getCatBitmap(context: Context) {
        val loader = ImageLoader(context)
        val request = LoadRequest.Builder(context)
            .data(cat.imageUrl)
            .target { result ->
                _catData.value = (result as BitmapDrawable).bitmap
            }
            .build()
        loader.execute(request)
    }
}
