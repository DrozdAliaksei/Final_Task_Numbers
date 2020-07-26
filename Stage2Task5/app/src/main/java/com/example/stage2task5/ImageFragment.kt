package com.example.stage2task5

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.stage2task5.vm.CatImageViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.image_activity.fab
import kotlinx.android.synthetic.main.image_activity.fullImage
import java.io.IOException

class ImageFragment : Fragment() {

    private lateinit var bitmap: Bitmap
    private val catImageViewModel by viewModels<CatImageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catImageViewModel.saveCat(arguments)
        catImageViewModel.getCatBitmap(requireContext())
        catImageViewModel.catdata.observe(viewLifecycleOwner) {
            fullImage.setImageBitmap(it)
            fab.setOnClickListener {
                if (!isWriteExternalStorageGranted()) {
                    requestPermissions()
                } else {
                    if (saveImageToGallery(bitmap)) {
                        Snackbar.make(
                            view,
                            "This image has been saved to the gallery",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Action", null).show()
                    } else {
                        Snackbar.make(
                            view,
                            "Something went wrong during saving",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Action", null).show()
                    }
                }
            }
            bitmap = it
        }
    }

    private fun saveImageToGallery(bitmap: Bitmap): Boolean {
        val contentUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "Cat")
            put(MediaStore.Images.Media.DISPLAY_NAME, "Cat.jpg")
        }

        return try {
            val contentResolver = requireActivity().contentResolver
            val uri = contentResolver.insert(contentUri, contentValues)
                ?: throw IOException("Failed to create new MediaStore record.")
            contentResolver.openOutputStream(uri).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            true
        } catch (exp: Exception) {
            false
        }
    }

    private fun isWriteExternalStorageGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE
        )
    }

    private companion object {
        private const val REQUEST_CODE = 100
    }
}
