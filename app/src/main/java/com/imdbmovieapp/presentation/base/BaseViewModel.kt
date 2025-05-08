package com.imdbmovieapp.presentation.base

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.imdbmovieapp.presentation.model.MoviesResultsUI
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.movieStates.MovieState
import com.imdbmovieapp.utils.navCommand.NavigationCommand
import com.imdbmovieapp.utils.lifecycleScopeExtensions.viewModelScope
import com.imdbmovieapp.utils.movieConstants.MovieConstants.ERROR
import com.imdbmovieapp.utils.movieConstants.MovieConstants.FAILED_TO_LOAD
import com.imdbmovieapp.utils.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation: SharedFlow<NavigationCommand> get() = _navigation
    private var dialog: Dialog? = null

    fun navigateTo(fragment: Fragment) {
        viewModelScope {
            _navigation.emit(NavigationCommand.ToDirection(fragment))
        }
    }

    fun navigateBack() {
        viewModelScope {
            _navigation.emit(NavigationCommand.Back)
        }
    }

    protected fun showDialog(context: Context) {
        dialog = Dialog(context)
        with(dialog!!) {
            setContentView(com.imdbmovieapp.R.layout.spinner_loading)
            setCancelable(false)
            show()
        }
    }

    protected fun errorToast(error: String, context: Context) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    protected fun hideDialog() {
        if (dialog != null) dialog!!.hide()
    }

    protected fun <T> fetchMovies(
        useCase: suspend () -> Flow<Resource<T>>,
        stateFlow: MutableStateFlow<MovieState>,
        context: Context,
        resultsMapper: (T) -> List<MoviesResultsUI>,
        showDialogOnClick: () -> Unit,
        hideDialogOnClick: () -> Unit
    ) {

        viewModelScope {
            useCase().collect { state ->
                when (state) {
                    is Resource.Error -> {
                        stateFlow.value = MovieState(error = state.message ?: ERROR)
                        errorToast(FAILED_TO_LOAD, context)
                        hideDialogOnClick.invoke()
                    }

                    is Resource.Loading -> {
                        stateFlow.value = MovieState(isLoading = true)
                        showDialogOnClick.invoke()
                    }

                    is Resource.Success -> {
                        stateFlow.value = MovieState(
                            movieList = resultsMapper(state.data!!)
                        )
                        hideDialogOnClick.invoke()
                    }
                }
            }
        }
    }
}