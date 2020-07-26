package com.example.stage2task5.data.datasource.api.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cat(
    val id: String?,
    val imageUrl: String?
) : Parcelable
