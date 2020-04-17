package com.ashleyfigueira.coronatracker.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ashleyfigueira.coronatracker.R
import kotlinx.android.synthetic.main.view_nointernet_state.view.*

class NoInternetStateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_nointernet_state, this, true)
        noInternetLabel.text = context.getString(R.string.nointernet_view_title)
    }

    fun setBodyText(@StringRes bodyText: Int) {
        noInternetLabel.text = context.getString(bodyText)
    }

    fun setTextColor(@ColorRes color: Int) {
        noInternetLabel.setTextColor(ContextCompat.getColor(context, color))
    }
}