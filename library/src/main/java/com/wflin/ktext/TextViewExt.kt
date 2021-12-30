package com.wflin.ktext

import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @author: wflin
 * @data: 2021/12/14
 * @desc: TextView extension file
 */

/**
 * provide simpler access to {@link TextView#setTextColor(int)}
 */
fun TextView.textColor(@ColorRes id: Int) {
    setTextColor(ContextCompat.getColor(context, id))
}

/**
 * set underline text on TextView
 */
fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * set bold text on TextView
 */
fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}

/**
 * set delete line text on TextView
 */
fun TextView.deleteLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * Extension method to set font for TextView.
 */
fun TextView.font(font: String) {
    typeface = Typeface.createFromAsset(context.assets, "fonts/$font.ttf")
}

/**
 * set ForegroundColorSpan for TextView
 */
fun TextView.setColorSpan(str: String, @ColorRes id: Int) {
    try {
        val span = SpannableString(text)
        val start = text.indexOf(str);
        span.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, id)),
            start,
            start + str.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = span
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

/**
 * set ClickSpan for TextView
 */
fun TextView.setClickSpan(
    str: String,
    @ColorInt color: Int,
    block: () -> Unit,
    isUnderlineText: Boolean = false
) {
    try {
        val span = SpannableString(text)
        val start = text.indexOf(str)
        val clickSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                block()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = color
                ds.isUnderlineText = isUnderlineText
            }
        }
        span.setSpan(
            clickSpan,
            start,
            start + str.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

/**
 * Extension method to set a drawable to the left of TextView.
 */
fun TextView.setDrawableLeft(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0)
}

