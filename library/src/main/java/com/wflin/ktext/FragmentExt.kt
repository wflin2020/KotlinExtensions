package com.wflin.ktext

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * @author: wflin
 * @data: 2021/12/20
 * @desc: Fragment extension file
 */

/**
 * create an intent for [T]
 */
inline fun <reified T> Fragment.intent() = Intent(requireContext(), T::class.java)

/**
 * create an intent for and apply a lambda on it
 */
inline fun <reified T> Fragment.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(requireContext(), T::class.java)
    intent.body()
    return intent
}

/**
 * provide simpler access to {@link Fragment#startActivity(Intent)}
 */
inline fun <reified T> Fragment.launch(finish: Boolean = false) {
    startActivity(Intent(requireContext(), T::class.java))
    if (finish) {
        requireActivity().finish()
    }
}

/**
 * provide simpler access to {@link Fragment#startActivity(Intent)}
 */
inline fun <reified T> Fragment.launch(
    body: Intent.() -> Unit,
    finish: Boolean = false
) {
    val intent = Intent(requireContext(), T::class.java)
    intent.body()
    startActivity(intent)
    if (finish) {
        requireActivity().finish()
    }
}

