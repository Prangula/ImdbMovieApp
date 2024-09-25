package com.imdbmovieapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.imdbmovieapp.utils.NavigationCommand
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
}