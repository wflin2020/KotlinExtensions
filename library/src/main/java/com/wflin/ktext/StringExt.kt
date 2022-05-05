package com.wflin.ktext

import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Base64
import androidx.annotation.ColorInt
import java.io.File

/**
 * @author: wflin
 * @data: 2021/12/15
 * @desc: String extension file
 */

val String.decodeBase64: String
    get() = Base64.decode(this, Base64.DEFAULT).toString(Charsets.UTF_8)

val String.encodeBase64: String
    get() = Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.DEFAULT)

/**
 * get file uri by file path
 */
fun String.fileUri(): Uri = Uri.fromFile(File(this))

/**
 * set ForegroundColorSpan for String
 */
fun String.colorSpan(str: String, color: Int): SpannableString {
    val span = SpannableString(this)
    try {
        val start = this.indexOf(str)
        span.setSpan(
            ForegroundColorSpan(color),
            start,
            start + str.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return span
}

/**
 * provide simpler access to {@link Color#parseColor(String)}
 */
val String.asColor: Int
    @ColorInt get() = Color.parseColor(this)

/**
 * provide safer access to {@link String#toInt()}
 */
fun String?.toIntSafe(defaultValue: Int = 0): Int =
    this?.toIntOrNull() ?: defaultValue

/**
 * provide safer access to {@link String#toDouble()}
 */
fun String?.toDoubleSafe(defaultValue: Double = 0.0): Double =
    this?.toDoubleOrNull() ?: defaultValue

/**
 * provide safer access to {@link String#toLong()}
 */
fun String?.toLongSafe(defaultValue: Long = 0L): Long =
    this?.toLongOrNull() ?: defaultValue

/**
 * provide safer access to {@link String#toFloat()}
 */
fun String?.toFloatSafe(defaultValue: Float = 0f): Float =
    this?.toFloatOrNull() ?: defaultValue

/**
 * provide safer access to {@link String#toShort()}
 */
fun String?.toShortSafe(defaultValue: Short = 0): Short =
    this?.toShortOrNull() ?: defaultValue

/**
 * provide safer access to {@link String#toByte()}
 */
fun String?.toByteSafe(defaultValue: Byte = 0): Byte =
    this?.toByteOrNull() ?: defaultValue