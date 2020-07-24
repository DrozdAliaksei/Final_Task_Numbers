package com.example.stage2task5

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import coil.api.load
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ImageFragment : Fragment() {
    private val TAG = "ImageFragment"

//    private val catImageViewModel by viewModels<CatImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = arguments?.getString("Cat")
//        catImageViewModel.saveCat(arguments)

        Log.e(TAG, "$imageUrl  +  $id")
        val imageView: ImageView = view.findViewById(R.id.fullImage)
        imageView.load(imageUrl) {
            crossfade(true)
        }
        id.let { }

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            saveImageToGallery(imageView)
            Snackbar.make(view, "This image has been saved to the gallery", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun saveImageToGallery(imageView: ImageView): Boolean {
        return try {
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, "Cat")
                put(MediaStore.Images.Media.DISPLAY_NAME, "Cat")
            }
            val contentResolver = requireActivity().contentResolver
            val drawable = imageView.drawable.toBitmap()
            val url =
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            if (url != null) {
//                val outputStream = contentResolver.openOutputStream(url)
                contentResolver.openOutputStream(url).use {
                    drawable.compress(Bitmap.CompressFormat.JPEG, 85, it)
                }
            }
            true
        } catch (exp: Exception) {
            false
        }
    }
}
