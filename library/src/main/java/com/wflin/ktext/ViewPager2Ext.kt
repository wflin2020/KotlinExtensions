package com.wflin.ktext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.max

/**
 * @author: wflin
 * @data: 2021/12/21
 * @desc: ViewPager2 extension file
 */

fun ViewPager2.init(
    fragment: Fragment,
    fragmentList: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = false
) {
    this.isUserInputEnabled = isUserInputEnabled
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

    }
}

/**
 * provide simpler access to set onPageScrollStateChanged listener
 */
fun ViewPager2.onPageScrollStateChanged(
    block: (state: Int) -> Unit
) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            block(state)
        }
    })
}

/**
 * provide simpler access to set onPageScrolled listener
 */
fun ViewPager2.onPageScrolled(
    block: (
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) -> Unit
) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            block(position, positionOffset, positionOffsetPixels)
        }
    })
}

/**
 * provide simpler access to set onPageSelected listener
 */
fun ViewPager2.onPageSelected(
    block: (position: Int) -> Unit
) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            block(position)
        }
    })
}

/**
 * provide simpler access to ViewPager2#registerOnPageChangeCallback
 */
fun ViewPager2.setPageChangeCallback(
    onStateChanged: (state: Int) -> Unit = { _ -> },
    onScrolled: (pos: Int, posOffset: Float, posOffsetPixels: Int) -> Unit = { _, _, _ -> },
    onSelected: (position: Int) -> Unit = { _ -> }
) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            onStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            onScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onSelected(position)
        }
    })
}

/**
 * provide simpler access to back previous item
 */
fun ViewPager2.back(animate: Boolean = true) {
    val pos = max(currentItem -1, 0)
    setCurrentItem(pos, animate)
}