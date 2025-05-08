package com.imdbmovieapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResultsUI(
    var id: Int = 0,
    var name: String = ""
):Parcelable
