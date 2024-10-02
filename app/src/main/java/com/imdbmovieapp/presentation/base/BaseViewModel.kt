package com.imdbmovieapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.paging.PagingData
import com.imdbmovieapp.utils.nav_command.NavigationCommand
import com.imdbmovieapp.utils.lifecycle_scope_extensions.viewModelScope
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation: SharedFlow<NavigationCommand> get() = _navigation

    fun navigateTo(action: NavDirections) {
        viewModelScope {
            _navigation.emit(NavigationCommand.ToDirection(action))
        }
    }

    fun navigateBack() {
        viewModelScope {
            _navigation.emit(NavigationCommand.Back)
        }
    }

    fun <T, R : Any> getMovies(
        useCaseCall: suspend () -> Resource<T>,
        mapper: (T) -> R,
        stateFlow: MutableStateFlow<Resource<R>>
    ) {
        viewModelScope {
            useCaseCall().let { resource ->
                stateFlow.value = when (resource) {
                    is Resource.Success -> {
                        Resource.Success(mapper(resource.data!!))
                    }

                    is Resource.Error -> {
                        Resource.Error(message = resource.message.toString())
                    }

                    is Resource.Loading -> {
                        Resource.Loading()
                    }
                }
            }
        }
    }
}