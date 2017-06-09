package com.kotlinmobile.cn.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.adapter.PageAdapter
import com.kotlinmobile.cn.log
import com.kotlinmobile.cn.model.ListDetail
import com.kotlinmobile.cn.model.Page
import com.kotlinmobile.cn.network.ListDetailSource
import com.kotlinmobile.cn.snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_detatil.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by fengxing on 2017/6/8.
 */
class ListCartoonDetailActivity : AppCompatActivity() {

    companion object {
        var COVER_URL = "Url"
        var COVER_TITLE = "title"
        var COVER_LINK = "link"
    }

    lateinit var link: String
    lateinit var url: String
    lateinit var title: String
    lateinit var adapter: PageAdapter
    lateinit var mData: ArrayList<Page>
    lateinit var listDetail: ListDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detatil)
        link = intent.getStringExtra(COVER_LINK)
        title = intent.getStringExtra(COVER_TITLE)
        url = intent.getStringExtra(COVER_URL)
        initView()
    }

    fun initView() {

        setSupportActionBar(list_detail_toolbar)
        collapsing_toolbar_layout.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.with(this).load(url).into(iv_list_detail)

        list_detail_swipe.setOnRefreshListener { load() }
        list_detail_swipe.post { list_detail_swipe.isRefreshing = true }

        list_detail_recycler_view.layoutManager = GridLayoutManager(this, 4)
        adapter = PageAdapter { view: View, position: Int ->
            if (title.contains("SBS")) {
                //我不知道怎么回事
                log(" 这个标题的连接的问题 : " + title)
                log(url)
            } else {
                jump2Read(position)
            }
        }
        list_detail_recycler_view.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        list_detail_swipe.post { list_detail_swipe.isRefreshing = true }
        load()
    }

    fun load() {
        async {
            listDetail = ListDetailSource().obtain(link)
            mData = listDetail.pages as ArrayList<Page>
            uiThread {
                adapter.refreshData(mData)
                list_detail_swipe.isRefreshing = false
                if (listDetail.size() == 0) {
                    showError()
                }
            }
        }
    }

    fun jump2Read(position: Int) {
        var intent = Intent(this, ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].url)
        startActivity(intent)
    }

    fun showError() {
        list_detail_recycler_view.snackbar("卧槽 - -！这是啥")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (R.id.menu_list_detail.equals(item?.itemId)) {
            var info = listDetail.detail
            list_detail_recycler_view.snackbar(info.description + "\n" + info.updateTime)
        } else if (android.R.id.home == (item?.itemId)) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}