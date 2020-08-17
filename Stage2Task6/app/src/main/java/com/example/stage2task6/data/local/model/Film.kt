package com.example.stage2task6.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String?,
    val previewImageUrl: String?,
    val duration: String?,
    val mediaUrl: List<String>?,
    val description: String?
) : Parcelable
