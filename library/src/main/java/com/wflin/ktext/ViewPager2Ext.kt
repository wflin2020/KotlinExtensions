package com.wflin.ktext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

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