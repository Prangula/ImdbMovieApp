package com.imdbmovieapp.presentation.screen.homeMoviesFragment.ui

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.FragmentHomeMoviesBinding
import com.imdbmovieapp.presentation.base.BaseFragment
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.adapter.PopularMoviesAdapter
import com.imdbmovieapp.presentation.screen.homeMoviesFragment.vm.HomeViewModel
import com.imdbmovieapp.utils.Resource
import com.imdbmovieapp.utils.lifeCycleScopeExtensions.observe
import kotlin.reflect.KClass

class HomeMoviesFragment : BaseFragment<FragmentHomeMoviesBinding, HomeViewModel>(
    FragmentHomeMoviesBinding::inflate
) {
    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class
    private lateinit var adapter: PopularMoviesAdapter

    override fun onBind() {
        adapter = PopularMoviesAdapter()
        popularMoviesObserver()
        popularMoviesRecyclerView()
        binding.customSearchBar.showGenreTags(binding.homeGenresChipGroup)
        check()
    }

    private fun popularMoviesRecyclerView() {
        with(binding) {
            homeMoviesRecyclerView.adapter = adapter
            homeMoviesRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun check() {


        viewModel.getPopularMovies()
        binding.homeGenresChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.genrePopularChip -> {
                    viewModel.getPopularMovies()
                }

                R.id.genreTopRatedChip -> {
                    Toast.makeText(requireContext(), "hello", Toast.LENGTH_SHORT).show()
                    adapter.submitList(emptyList())
                }
            }
        }
    }

    private fun popularMoviesObserver() {
        observe(viewModel.popularMovies) { resource ->
            when (resource) {
                is Resource.Success -> {
                    adapter.submitList(resource.data!!)
                }

                is Resource.Error -> {
                    binding.homeMoviesTextview.text = resource.message
                }

                is Resource.Loading -> {
                }
            }
        }
    }
}