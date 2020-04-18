package com.ashleyfigueira.coronatracker.common

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    lp.setMargins(left ?: lp.leftMargin, top ?: lp.topMargin, right ?: lp.rightMargin, bottom ?: lp.bottomMargin)
    layoutParams = lp
}

fun View.gone() { this.visibility = View.GONE }

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.INVISIBLE }

fun View.setVisible(visible: Boolean) { this.visibility = if (visible) View.VISIBLE else View.GONE }

fun View.setGone(isGone: Boolean) { this.visibility = if (isGone) View.GONE else View.VISIBLE }

fun View.setInvisible(isInvisible: Boolean) { this.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE }