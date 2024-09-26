package com.imdbmovieapp.utils

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.ChipGroup
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomSearchBarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = CustomSearchBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    private var isDefault = true
    private var job: Job? = null

    fun showGenreTags(chipGroup: ChipGroup) {
        with(binding) {
            customImageView.setOnClickListener {
                if (!isDefault) {
                    imageBackgroundHelper(
                        customImageView,
                        R.drawable.ic_show_tags,
                        R.drawable.bkg_circle_yellow_stroke
                    )
                    chipGroup.visibility = View.GONE
                } else {
                    imageBackgroundHelper(
                        customImageView,
                        R.drawable.ic_hide_tags,
                        R.drawable.bkg_button_circle_yellow_solid
                    )
                    showImageWithAnimation(chipGroup)
                    chipGroup.visibility = View.VISIBLE
                }
                isDefault = !isDefault
            }
        }
    }

    @SuppressLint("ServiceCast")
    fun hideKeyboard() {
        binding.customEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.customEditText.clearFocus()
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(binding.customEditText.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    fun getSearchMovies(
        viewModel: (query: String) -> Unit,
        lifecycleScope: CoroutineScope,
        chipGroup: ChipGroup
    ) {
        binding.customEditText.addTextChangedListener { search ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(500)
                viewModel(search.toString())
            }
            chipGroup.visibility = View.GONE
            binding.customImageView.visibility = View.INVISIBLE
            binding.customTextview.visibility = View.VISIBLE
        }
    }

    fun clickCancel(onClickAction: () -> Unit) {
        with(binding) {
            customTextview.setOnClickListener {
                imageBackgroundHelper(
                    customImageView,
                    R.drawable.ic_show_tags,
                    R.drawable.bkg_circle_yellow_stroke
                )
                customEditText.editableText.clear()
                customImageView.visibility = View.VISIBLE
                customTextview.visibility = View.GONE
                customEditText.clearFocus()
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(binding.customEditText.windowToken, 0)
                onClickAction.invoke()
            }
        }
    }

    private fun imageBackgroundHelper(
        imageView: ImageView,
        imageDrawable: Int,
        backgroundDrawable: Int
    ) {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                imageDrawable
            )
        )
        imageView.setBackgroundDrawable(
            ContextCompat.getDrawable(context, backgroundDrawable)
        )
    }

    private fun showImageWithAnimation(chipGroup: ChipGroup) {
        val animator = ObjectAnimator.ofFloat(chipGroup, "alpha", 0f, 1f)
        animator.duration = 1000
        animator.start()
    }
}