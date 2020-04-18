package com.ashleyfigueira.coronatracker.common

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    lp.setMargins(left ?: lp.leftMargin, top ?: lp.topMargin, right ?: lp.rightMargin, bottom ?: lp.bottomMargin)
    layoutParams = lp
}

fun View.setWeight(weight: Float) {
    val param = LinearLayout.LayoutParams(
        layoutParams.width,
        layoutParams.height,
        weight
    )

    param.marginEnd = dp2px(context, 5f)

    layoutParams = param
}

fun dp2px(context: Context, dp: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun View.gone() { this.visibility = View.GONE }

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.INVISIBLE }

fun View.setVisible(visible: Boolean) { this.visibility = if (visible) View.VISIBLE else View.GONE }

fun View.setGone(isGone: Boolean) { this.visibility = if (isGone) View.GONE else View.VISIBLE }

fun View.setInvisible(isInvisible: Boolean) { this.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE }