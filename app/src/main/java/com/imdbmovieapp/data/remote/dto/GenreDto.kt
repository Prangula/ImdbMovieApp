package com.imdbmovieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreDto(
    val id: Int,
    @SerializedName("name")
    val name: String
)