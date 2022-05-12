package com.wflin.ktextdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wflin.ktext.colorSpan
import com.wflin.ktext.decodeBase64
import com.wflin.ktext.encodeBase64
import com.wflin.ktext.toLongSafe
import com.wflin.ktextdemo.databinding.FragmentStringExtBinding

class StringExtFragment : Fragment() {

    private lateinit var binding: FragmentStringExtBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStringExtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        // colorSpan
        val text = "https://github.com/wflin2020/KotlinExtensions"
        binding.tv.text = text.colorSpan("StringExt.kt", R.color.purple_700)

        // asColor
        // binding.tv.text = text.colorSpan("KotlinExtensions", "#ffcc0000".asColor)

        // encodeBase64, decodeBase64
        binding.tv1.text = text.encodeBase64
        binding.tv2.text = binding.tv1.text.toString().decodeBase64

        // toIntSafe, the same: toDoubleSafe, toLongSafe, toFloatSafe, toShortSafe, toByteSafe
        val value = "test message".toLongSafe(1024)
        binding.tv3.text = "value is $value"

//        // fileUri
//        val uri = "${context?.applicationContext?.cacheDir}/indexInCache.html".fileUri()
//        binding.tv.text = uri.toString()
//        binding.webView.apply {
//            settings.allowContentAccess = true;
//            settings.allowFileAccess = true
//            loadUrl(uri.toString())
//        }
    }
}