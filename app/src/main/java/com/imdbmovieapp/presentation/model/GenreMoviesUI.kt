package com.imdbmovieapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GenreMoviesUI(
    var genres: @RawValue List<GenreResultsUI> = emptyList()
) : Parcelable