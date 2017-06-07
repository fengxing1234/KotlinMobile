package com.kotlinmobile.cn.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.activity.ComicActivity
import com.kotlinmobile.cn.adapter.CoverAdapter
import com.kotlinmobile.cn.model.Cover
import com.kotlinmobile.cn.network.CoverSource
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fengxing on 2017/5/31.
 */
class OnLineCartoonFragment : Fragment() {

    companion object {
        val AIM_URL = "http://ishuhui.net/?PageIndex=1"
    }

    var mData = ArrayList<Cover>()
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CoverAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_online, null, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    fun initView(view: View?) {

        swipeRefresh = view?.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView

        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        adapter = CoverAdapter { view: View, position: Int -> jump2Comic(position) }
        recyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            load()
        }

        swipeRefresh.post { swipeRefresh.isRefreshing = true }
    }

    fun load() {
        async() {
            var data = CoverSource().obtain(AIM_URL)
            uiThread {
                mData = data
                adapter.getData(mData)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    fun jump2Comic(position: Int) {
        var intent = Intent(context, ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].link)
        startActivity(intent)
    }

    /**
     * 实现懒加载 fragment的UI是否可见的
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }
}
