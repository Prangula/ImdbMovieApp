package com.imdbmovieapp.utils.lifecycle_scope_extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(
    action: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch { action() }
}

fun <T> LifecycleOwner.observe(
    stateFlow: StateFlow<T>,
    action: (T) -> Unit
) {
    lifecycleScope.launchWhenStarted {
        stateFlow.collect { item ->
            action(item)
        }
    }
}

fun Fragment.lifeCycleScope(
    action: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        action()
    }
}