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
import com.kotlinmobile.cn.activity.ListCartoonDetailActivity
import com.kotlinmobile.cn.adapter.CoverAdapter
import com.kotlinmobile.cn.model.Cover
import com.kotlinmobile.cn.network.ListCartoonSource
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fengxing on 2017/5/31.
 */
class ListCartoonFragment : Fragment() {

    companion object {
        var URL = "http://ishuhui.net/ComicBookList/"
    }

    lateinit var recycleView: RecyclerView
    lateinit var adapter: CoverAdapter
    lateinit var swipe: SwipeRefreshLayout
    var mData = ArrayList<Cover>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var inflate = inflater.inflate(R.layout.fragment_list_cartoon, container, false)
        recycleView = inflate.findViewById(R.id.list_recycler_view) as RecyclerView
        swipe = inflate.findViewById(R.id.list_swipe_refresh) as SwipeRefreshLayout
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view: View) {
        recycleView.layoutManager = GridLayoutManager(context, 2)
        adapter = CoverAdapter { view: View, position: Int -> jump2Detail(position) }
        recycleView.adapter = adapter
        swipe.setOnRefreshListener { load() }
        swipe.post { swipe.isRefreshing = true }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    fun jump2Detail(position: Int) {
        var intent = Intent(context, ListCartoonDetailActivity().javaClass)
        intent.putExtra(ListCartoonDetailActivity.COVER_URL, mData[position].coverUrl)
        intent.putExtra(ListCartoonDetailActivity.COVER_TITLE, mData[position].title)
        intent.putExtra(ListCartoonDetailActivity.COVER_LINK, mData[position].link)
        startActivity(intent)
    }

    fun load() {
        async {
            var data = ListCartoonSource().obtain(URL)
            uiThread {
                swipe.isRefreshing = false
                mData = data
                adapter.getData(data)
            }
        }
    }
}
