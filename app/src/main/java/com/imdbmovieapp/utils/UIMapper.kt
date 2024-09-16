package com.imdbmovieapp.utils

interface UIMapper<in MODEL_A, out MODEL_B> {

    fun mapModel(model: MODEL_A): MODEL_B

    fun mapToList(modelList: List<MODEL_A>): List<MODEL_B> {
        return modelList.map { mapModel(it) }
    }
}