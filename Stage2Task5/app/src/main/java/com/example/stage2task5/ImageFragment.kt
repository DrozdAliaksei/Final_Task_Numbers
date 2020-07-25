package com.example.stage2task5

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
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
import java.io.IOException

class ImageFragment : Fragment() {
    private val TAG = "ImageFragment"

//    private val catImageViewModel by viewModels<CatImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
            if (saveImageToGallery(imageView)) {
                Snackbar.make(
                    view,
                    "This image has been saved to the gallery",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Action", null).show()
            } else {
                Snackbar.make(
                    view,
                    "Something went wrong while saving",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Action", null).show()
            }
        }
    }

    private fun saveImageToGallery(imageView: ImageView): Boolean {
        val contentUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "Cat")
            put(MediaStore.Images.Media.DISPLAY_NAME, "Cat")
        }

        return try {
            val contentResolver = requireActivity().contentResolver
            val uri = contentResolver.insert(contentUri, contentValues)
                ?: throw IOException("Failed to create new MediaStore record.")

            contentResolver.openOutputStream(uri).use {
                imageView.drawable.toBitmap().compress(Bitmap.CompressFormat.PNG, 85, it)
            }
            true
        } catch (exp: Exception) {
            Log.e(TAG, "Something went wrong while saving: $exp")
            false
        }
    }
}
