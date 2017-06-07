package com.kotlinmobile.cn.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kotlinmobile.cn.fragment.ComicFragment
import com.kotlinmobile.cn.model.Comic

/**
 * Created by fengxing on 2017/6/7.
 */
class ComicPagerAdapter(var mData: ArrayList<Comic>, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment = newComicFragment(mData[p0].url)


    override fun getCount(): Int = mData.size

    fun refreshData(data: ArrayList<Comic>) {
        mData = data
        notifyDataSetChanged()
    }

    fun newComicFragment(url: String): Fragment {
        return ComicFragment(url)
    }

}