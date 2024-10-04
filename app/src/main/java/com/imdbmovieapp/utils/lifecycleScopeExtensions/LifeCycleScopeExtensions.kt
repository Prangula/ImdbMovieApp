package com.imdbmovieapp.utils.lifecycleScopeExtensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun ViewModel.viewModelScope(
    action: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch { action() }
}

fun <T> LifecycleOwner.observe(
    flow: Flow<T>,
    action: (T) -> Unit
) {
    lifecycleScope.launch {
        flow.collect { data ->
            action(data)
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