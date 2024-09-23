package com.imdbmovieapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.imdbmovieapp.R
import com.imdbmovieapp.databinding.CustomSearchBarBinding

class CustomSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = CustomSearchBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    private var isDefault = true

    fun showGenreTags() {
        with(binding) {
            customImageView.setOnClickListener {
                if (!isDefault) {
                    imageBackgroundHelper(
                        customImageView,
                        R.drawable.ic_show_tags,
                        R.drawable.bkg_circle_yellow_stroke
                    )
                } else {
                    imageBackgroundHelper(
                        customImageView,
                        R.drawable.ic_hide_tags,
                        R.drawable.bkg_button_circle_yellow_solid
                    )
                }
                isDefault = !isDefault
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
}