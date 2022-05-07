package com.wflin.ktextdemo

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wflin.ktext.*
import com.wflin.ktextdemo.databinding.ActivityContextExtBinding

class ContextExtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContextExtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContextExtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContextExt()
        setTextViewExt()
    }

    private fun setContextExt() {
        binding.tv.apply {
            // getColorCompact (the same: getBoolean, getInteger, getDrawableCompat)
            setBackgroundColor(getColorCompat(R.color.design_default_color_secondary))
            val hasPermission = hasPermission(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            // versionCode, versionName, assetsToString
            val content = """
                hasPermission:$hasPermission,
                versionCode:$versionCode,
                versionName:$versionName,
                inputManager:$inputManager,
                connectivityManager:$connectivityManager,
                ktext_info:${assetsToString("ktext.json")}
            """.trimIndent()
            text = content
        }

        binding.btnLaunch.click {
            // 1.ContextExt#intent(body: Intent.() -> Unit)
            /***
             * val intentMain = intent<MainActivity> {
             * putExtra("info", binding.tv.text.toString())
             * }
             * startActivity(intentMain)
             */

            // 2.ContextExt#intent() -> startActivity(intent<MainActivity>())

            // 3.ContextExt#launch ->  launch<MainActivity>()

            // 4.ContextExt#launch(body: Intent.() -> Unit)
            launch<MainActivity> {
                putExtra("info", binding.tv.text.toString())
            }


        }
    }

    private fun setTextViewExt() {
        binding.tv.apply {
            // textColor
            textColor(R.color.white)
            // font
            font(R.font.noto_serif)
            // setColorSpan, setClickSpan,
            setColorSpan("wflin2020", R.color.design_default_color_error)
            setClickSpan("library", "#ff99cc00".asColor, {
                Toast.makeText(
                    this@ContextExtActivity,
                    "\uD83D\uDD25 KotlinExtensions", Toast.LENGTH_SHORT
                ).show()
            }, true)
            // setDrawableTop (the same: setDrawableLeft, setDrawableRight, setDrawableBottom)
            setDrawableTop(R.drawable.ic_launcher_foreground, 5)
        }
    }
}