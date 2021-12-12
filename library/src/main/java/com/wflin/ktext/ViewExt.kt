package com.wflin.ktext

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.UiThread
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.*

/**
 * @author: wflin
 * @data: 2021/12/11
 * @desc: view extension method file
 */

/**
 * visibility = View.VISIBLE
 */
@UiThread
fun View.toVisible() {
    visibility = View.VISIBLE
}

/**
 * show the view if [condition] return true
 */
@UiThread
inline fun View.showIf(condition: () -> Boolean) {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
}

/**
 * visibility = View.GONE
 */
@UiThread
fun View.toGone() {
    visibility = View.GONE
}

/**
 * hide the view if [condition] return true
 */
@UiThread
inline fun View.hideIf(condition: () -> Boolean) {
    if (visibility != View.GONE && condition()) {
        visibility = View.GONE
    }
}

/**
 * visibility = View.INVISIBLE
 */
@UiThread
fun View.toInvisible() {
    visibility = View.INVISIBLE
}

/**
 * show the keyboard for view
 */
fun View.showKeyboard() {
    val imm = context.applicationContext.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * hide the keyboard for view
 * @return whether it worked
 */
fun View.hideKeyboard(): Boolean {
    return try {
        val imm = context.applicationContext.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    } catch (ex: Exception) {
        ex.printStackTrace()
        false
    }
}

/**
 * set View's width
 */
@UiThread
fun View.setWidth(width: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = width
        layoutParams = it
    }
}

/**
 * set View's width
 */
@UiThread
fun View.setHeight(height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = height
        layoutParams = it
    }
}

/**
 * resize View with width & height
 */
@UiThread
fun View.resize(width: Int, height: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = width
        lp.height = height
        layoutParams = it
    }
}

/**
 * set View's top padding
 */
@UiThread
fun View.setPaddingTop(top: Int) =
    setPaddingRelative(paddingStart, top, paddingEnd, paddingBottom)

/**
 * set View's bottom padding
 */
@UiThread
fun View.setPaddingBottom(bottom: Int) =
    setPaddingRelative(paddingStart, paddingTop, paddingEnd, bottom)

/**
 * set View's left padding(RTL support)
 */
@UiThread
fun View.setPaddingStart(start: Int) =
    setPaddingRelative(start, paddingTop, paddingEnd, paddingBottom)

/**
 * set View's right padding(RTL support)
 */
@UiThread
fun View.setPaddingEnd(end: Int) =
    setPaddingRelative(paddingStart, paddingTop, end, paddingBottom)

/**
 * preventing fast click on view
 * @param delay: default delay time(600ms)
 */
fun View.click(block: () -> Unit, delay: Long = 600) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime = 0L

        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < delay) {
                return
            }
            block()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

/**
 * provide safer access to {@link View#postDelay(Runnable action, long delayMillis)}
 */
fun View.postDelayByLifecycle(
    delayMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
    lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
        delay(delayMillis)
        block()
    }
}