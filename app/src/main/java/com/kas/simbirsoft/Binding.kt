package com.kas.simbirsoft

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.kas.simbirsoft.extensions.getDate
import java.util.*

@BindingAdapter("setVisibility")
fun View.setVisibility(visibility: Boolean?) {
    visibility?.let {
        isVisible = it
    } ?: run { isVisible = false }
}

@BindingAdapter("setButtonText")
fun MaterialButton.setButtonText(date: Calendar?) {
    this.text = date?.getDate() ?: resources.getString(R.string.text_pick_date)
}