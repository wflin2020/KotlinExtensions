package com.wflin.ktext

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * @author: wflin
 * @data: 2021/12/16
 * @desc: Density extension file
 */

/**
 * dp to px
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * dp to px
 */
val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

/**
 * sp to px
 */
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

/**
 * sp to px
 */
val Int.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

/**
 * px to dp
 */
val Float.pxToDp
    get() = this / Resources.getSystem().displayMetrics.density

/**
 * px to dp
 */
val Int.pxToDp
    get() = this / Resources.getSystem().displayMetrics.density

/**
 * px to sp
 */
val Float.pxToSp
    get() = this / Resources.getSystem().displayMetrics.scaledDensity

/**
 * px to sp
 */
val Int.pxToSp
    get() = this / Resources.getSystem().displayMetrics.scaledDensity

/**
 * get displayMetrics in Context
 */
val Context.displayMetrics: DisplayMetrics
    get() = resources.displayMetrics

/**
 * device width in pixels
 */
val Context.disPlayWidth: Int
    get() = resources.displayMetrics.widthPixels

/**
 * device height in pixels
 */
val Context.disPlayHeight: Int
    get() = resources.displayMetrics.heightPixels

