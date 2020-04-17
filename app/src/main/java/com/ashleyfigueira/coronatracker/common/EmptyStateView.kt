package com.ashleyfigueira.coronatracker.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ashleyfigueira.coronatracker.R
import kotlinx.android.synthetic.main.view_empty_state.view.*

class EmptyStateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_empty_state, this, true)
        empty_state_body.text = context.getString(R.string.empty_view_message)
    }

    fun setBodyText(@StringRes bodyText: Int) {
        empty_state_body.text = context.getString(bodyText)
    }

    fun setTextColor(@ColorRes color: Int) {
        empty_state_title.setTextColor(ContextCompat.getColor(context, color))
        empty_state_body.setTextColor(ContextCompat.getColor(context, color))
    }
}