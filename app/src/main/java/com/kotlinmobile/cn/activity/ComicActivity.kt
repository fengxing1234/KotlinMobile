package com.kotlinmobile.cn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.adapter.ComicPagerAdapter
import com.kotlinmobile.cn.model.Comic
import com.kotlinmobile.cn.network.ComicSource
import kotlinx.android.synthetic.main.activity_comic.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fengxing on 2017/5/31.
 */
class ComicActivity : AppCompatActivity() {

    companion object {
        val INTENT_COMIC_URL = "url"
    }

    lateinit var url: String

    var mData = ArrayList<Comic>()

    lateinit var adapter: ComicPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)
        url = intent.getStringExtra(INTENT_COMIC_URL)
        adapter = ComicPagerAdapter(mData, supportFragmentManager)
        comic_view_pager.adapter = adapter
        comic_view_pager.offscreenPageLimit = 3
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    fun load(){
        async {
            var data = ComicSource().obtain(url)
            uiThread {
                mData = data
                adapter.refreshData(data)
            }
        }
    }

}