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
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.ChipGroup
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomSearchBarBinding
import com.imdbmovieapp.utils.viewExtensions.hide
import com.imdbmovieapp.utils.viewExtensions.invisible
import com.imdbmovieapp.utils.viewExtensions.show
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
                    //TODO
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
        searchClickAction: (query: String, context: Context) -> Unit,
        lifecycleScope: CoroutineScope,
        chipGroup: ChipGroup,
        observer: () -> Unit,
        textView: TextView, imageView: ImageView
    ) {
        with(binding) {
            customEditText.addTextChangedListener { search ->
                job = lifecycleScope.launch {
                    observer.invoke()
                    delay(500)
                    searchClickAction(search.toString(), context)
                }
                chipGroup.visibility = View.GONE
                if (search.isNullOrEmpty()) {
                    job?.cancel()
                    textView.hide()
                    imageView.hide()
                } else {
                    customImageView.invisible()
                    customTextview.show()
                }
            }
        }
    }

    fun clickCancel(onClickAction: () -> Unit, textView: TextView, imageView: ImageView) {
        with(binding) {
            customTextview.setOnClickListener {
                onClickAction.invoke()
                imageBackgroundHelper(
                    customImageView,
                    R.drawable.ic_show_tags,
                    R.drawable.bkg_circle_yellow_stroke
                )
                customEditText.editableText.clear()
                customEditText.clearFocus()
                customImageView.show()
                customTextview.hide()
                textView.hide()
                imageView.hide()
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(binding.customEditText.windowToken, 0)
            }
        }
    }

    //Todo
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
        imageView.background = ContextCompat.getDrawable(context, backgroundDrawable)
    }

    private fun showImageWithAnimation(chipGroup: ChipGroup) {
        ObjectAnimator.ofFloat(chipGroup, context.getString(R.string.alpha), 0f, 1f).apply {
            duration = 1000
            start()
        }
    }
}