package com.wflin.ktextdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import com.wflin.ktext.*
import com.wflin.ktextdemo.databinding.FragmentViewExtBinding

class ViewExtFragment : Fragment() {

    private lateinit var binding: FragmentViewExtBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewExtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }


    @SuppressLint("SetTextI18n")
    private fun setup() {
        // click (the same: binding.btnSwitch.click({ }, 600) )
        var index = 0
        binding.btnSwitch.click {
            when (index) {
                0 -> {
                    // toGone(), the same: toVisible, toInvisible
                    binding.btn.toGone()
                    setState("toGone")
                }
                1 -> {
                    // showIf, the same: hideIf
                    binding.btn.showIf { binding.btn.isGone }
                    setState("showIf")
                }
                2 -> {
                    // showKeyboard
                    binding.etState.showKeyboard()
                    setState("showKeyboard")
                }
                3 -> {
                    // hideKeyboard
                    binding.etState.hideKeyboard()
                    setState("hideKeyboard")
                }
                4 -> {
                    // setWidth, setHeight
                    val w = binding.btn.width * 2
                    val h = binding.btn.height * 2
                    binding.btn.setWidth(width = w)
                    binding.btn.setHeight(height = h)
                    setState("setWidth， setHeight")
                }
                5 -> {
                    // resize
                    val w = binding.btn.width / 2
                    val h = binding.btn.height / 2
                    binding.btn.resize(w, h)
                    setState("resize")
                }
                6 -> {
                    // find
                    val tv = binding.root.find<TextView>(R.id.tv)
                    tv.text = "find"
                    setState(tv.text.toString())
                }
                7 -> {
                    // setPaddingTop, setPaddingBottom, setPaddingStart, setPaddingEnd
                    binding.tv.setPaddingTop(20f.dp.toInt())
                    binding.tv.setPaddingBottom(20f.dp.toInt())
                    binding.tv.setPaddingStart(20f.dp.toInt())
                    binding.tv.setPaddingEnd(20f.dp.toInt())
                    setState("setPadding")
                }
                8 -> {
                    // need acitvity-1.2.0、fragment-1.3.0、appcompat-1.3.0 or latest
                    // see https://developer.android.com/jetpack/androidx/releases/lifecycle
                    // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:version"
                    binding.tv.postDelayByLifecycle({
                        Toast.makeText(
                            context,
                            "\uD83D\uDD25 KotlinExtensions",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, 1500)
                    setState("postDelayByLifecycle")
                }
                else -> {
                    val padding = 5.dp.toInt()
                    binding.tv.setPaddingRelative(padding, padding, padding, padding)
                    val text = ""
                    binding.etState.setText(text.toCharArray(), 0, text.length)
                    index = -1
                }
            }
            index++
        }
    }

    private fun setState(method: String) {
        val text = "current method: $method"
        binding.etState.setText(text.toCharArray(), 0, text.length)
    }
}