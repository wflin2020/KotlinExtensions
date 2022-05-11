package com.wflin.ktextdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wflin.ktext.*
import com.wflin.ktextdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val fragList = mutableListOf<Fragment>(ViewExtFragment())
        // {@link ViewPager2Ext#init}, the same: ViewPager2.init(Fragment,MutableList<Fragment>, Boolean)
        binding.vp2.init(this, fragList)
        // back
        binding.btnBack.click {
            binding.vp2.back(true)
        }
        // next
        binding.btnNext.click {
            binding.vp2.next(false)
        }
        // setPageChangeCallback (the same: onPageScrollStateChanged, onPageScrolled, onPageSelected)
        binding.vp2.setPageChangeCallback(
            { state ->
                // onStateChanged
            },
            { pos: Int, posOffset: Float, posOffsetPixels: Int ->
                // onScrolled
            },
            { position ->
                // onSelected
            }
        )
    }
}