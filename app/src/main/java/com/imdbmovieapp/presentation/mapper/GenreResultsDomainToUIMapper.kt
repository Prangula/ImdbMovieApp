package com.imdbmovieapp.presentation.mapper

import com.imdbmovieapp.domain.model.GenreResultsDomain
import com.imdbmovieapp.presentation.model.GenreResultsUI
import com.imdbmovieapp.utils.base.BaseMapper

class GenreResultsDomainToUIMapper : BaseMapper<GenreResultsDomain, GenreResultsUI> {
    override fun mapModel(model: GenreResultsDomain): GenreResultsUI {
        return with(model) {
            GenreResultsUI(
                id = id,
                name = name
            )
        }
    }
}