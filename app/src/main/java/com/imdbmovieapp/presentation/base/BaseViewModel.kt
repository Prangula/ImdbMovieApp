package com.imdbmovieapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.imdbmovieapp.utils.NavigationCommand
import com.imdbmovieapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import com.imdbmovieapp.utils.lifeCycleScopeExtensions.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
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

    protected fun <T, R> getMovies(
        useCase: suspend (Unit) -> Resource<List<T>>,
        mapper: (T) -> R,
        stateFlow: MutableStateFlow<Resource<List<R>>>
    ) {
        viewModelScope {
            val result = useCase(Unit)
            stateFlow.value =
                when (result) {
                    is Resource.Success -> {
                        Resource.Success(result.data!!.map { mapper(it) })
                    }

                    is Resource.Error -> {
                        Resource.Error(result.message!!)
                    }

                    is Resource.Loading -> {
                        Resource.Loading()
                    }
                }
        }
    }
}