package com.kotlinmobile.cn.activity.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by fengxing on 2017/5/27.
 */

class ContentPagerAdapter(val fragments: ArrayList<Fragment>, val nameList: List<String>, val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment = fragments[p0]

    override fun getCount(): Int =fragments.size

    override fun getPageTitle(position: Int): CharSequence {
        return nameList[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }
}
