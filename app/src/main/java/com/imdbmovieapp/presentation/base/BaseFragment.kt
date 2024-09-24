package com.imdbmovieapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.imdbmovieapp.utils.NavigationCommand
import com.imdbmovieapp.utils.lifeCycleScopeExtensions.lifeCycleScope
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val viewBindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    abstract val viewModelClass: KClass<VM>
    protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
    }

    private fun observeNavigationCommands() {
        lifeCycleScope {
            viewModel.navigation.collect { command ->
                when (command) {
                    is NavigationCommand.ToDirection ->
                        findNavController().navigate(command.directions)

                    is NavigationCommand.Back ->
                        findNavController().navigateUp()
                }
            }
        }
    }

    protected abstract fun onBind()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}