package com.kotlinmobile.cn.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.adapter.InfoContentAdapter
import com.kotlinmobile.cn.model.NewsList
import com.kotlinmobile.cn.network.NewsListSource
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fengxing on 2017/5/31.
 */

class InfoCartoonFragment : Fragment() {

    companion object {
        val AIM_URL = "http://ishuhui.net/CMS/"
    }

    lateinit var info_swipe: SwipeRefreshLayout
    lateinit var info_recycle: RecyclerView
    lateinit var adapter: InfoContentAdapter
    var mData = ArrayList<NewsList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_info_cartoon, container, false)
        info_swipe = rootView.findViewById(R.id.info_cartoon_swipe) as SwipeRefreshLayout

        info_recycle = rootView.findViewById(R.id.info_cartoon_recycler) as RecyclerView
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        info_recycle.layoutManager = LinearLayoutManager(context)

        adapter = InfoContentAdapter()

        info_recycle.adapter = adapter

        info_swipe.setOnRefreshListener { load() }

        info_swipe.post { info_swipe.isRefreshing = true }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    fun load() {
        async() {
            var data = NewsListSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.getData(data)
                info_swipe.isRefreshing = false
            }
        }
    }


}