package com.imdbmovieapp.utils.lifeCycleScopeExtensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(
    action: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch { action() }
}
