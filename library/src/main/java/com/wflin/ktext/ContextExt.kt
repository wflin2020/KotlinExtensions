package com.wflin.ktext

import android.app.job.JobScheduler
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.lang.StringBuilder

/**
 * @author: wflin
 * @data: 2021/12/13
 * @desc: context extension file
 */

/**
 * provide simpler access to {@link ContextCompat#getColor(Context, int)}
 */
fun Context.getColorCompat(@ColorRes id: Int) =
    ContextCompat.getColor(this, id)

/**
 * provide simpler access to {@link ContextCompat#getDrawable(Context, int)}
 */
fun Context.getDrawableCompat(@DrawableRes id: Int) =
    ContextCompat.getDrawable(this, id)

/**
 * get connectivityManager for Context
 */
val Context.connectivityManager: ConnectivityManager
    get() = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

/**
 * get inputMethodManager for Context
 */
val Context.inputManager: InputMethodManager
    get() = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

/**
 * create an intent for [T]
 */
inline fun <reified T> Context.intent() =
    Intent(this, T::class.java)

/**
 * create an intent for and apply a lambda on it
 */
inline fun <reified T> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

/**
 * startActivity
 */
inline fun <reified T> Context.launch() =
    startActivity(Intent(this, T::class.java))

/**
 * provide simpler access to {@link Context#startActivity(Intent)}
 */
inline fun <reified T> Context.launch(body: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.body()
    startActivity(intent)
}

/**
 * provide simpler access to {@link Context#startService(Intent)}
 */
inline fun <reified T> Context.startService(): Intent {
    val service = Intent(this, T::class.java)
    startService(service)
    return service
}

/**
 * get application versionName
 */
val Context.versionName: String
    get() = packageManager.getPackageInfo(packageName, 0).versionName

/**
 * get application versionCode
 */
val Context.versionCode: String
    get() = packageManager.getPackageInfo(packageName, 0).versionName

/**
 * get string from assets file
 */
fun Context.assetsToString(fileName: String): String {
    StringBuilder().let {
        assets.open(fileName).bufferedReader().forEachLine { line ->
            it.append(line)
        }
        return it.toString()
    }
}

/**
 * copy content to clipboard
 */
fun Context.copyToClipboard(content: String) {
    val clipboardManager =
        ContextCompat.getSystemService(applicationContext, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("clipboard", content)
    clipboardManager?.setPrimaryClip(clip)
}

/**
 * check whether permission is granted.
 */
fun Context.hasPermission(vararg permissions: String) =
    permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

/**
 * Extension method to get jobScheduler for Context.
 */
inline val Context.jobScheduler: JobScheduler?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(JOB_SCHEDULER_SERVICE) as? JobScheduler