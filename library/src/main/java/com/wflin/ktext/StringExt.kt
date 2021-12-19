package com.wflin.ktext

import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Base64
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
 * get asset file uri by file name
 */
fun String.assetFileUri(): Uri = Uri.parse("file:///android_asset/$this")

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
            start+str.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return span
}