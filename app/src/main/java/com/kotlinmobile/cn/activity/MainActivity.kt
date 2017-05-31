package com.kotlinmobile.cn.activity.activity

import com.kotlinmobile.cn.activity.adapter.ContentPagerAdapter
import com.kotlinmobile.cn.fragment.InforCartoonFragment
import com.kotlinmobile.cn.fragment.ListCartoonFragment
import com.kotlinmobile.cn.fragment.OnLineCartoonFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : android.support.v7.app.AppCompatActivity() {

    //类似java中的静态成员
    companion object {
        val GITHUB_URL = "https://github.com/fengxing1234/KotlinMobile"
    }

    var resNameList = arrayListOf<Int>(com.kotlinmobile.cn.R.string.online_cartoon, com.kotlinmobile.cn.R.string.list_cartoon, com.kotlinmobile.cn.R.string.info_cartoon)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kotlinmobile.cn.R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        floating_action_button.setOnClickListener { jump2GitHub() }

        var fragments = ArrayList<android.support.v4.app.Fragment>()

        fragments.add(OnLineCartoonFragment())
        fragments.add(ListCartoonFragment())
        fragments.add(InforCartoonFragment())


        //this :: getString 代表啥意思
        var nameList = resNameList.map(this::getString)
        android.util.Log.d("tag", "nameList:" + nameList)

        view_pager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        //预加载页面的个数
        view_pager.offscreenPageLimit = 2
        tab_layout.setupWithViewPager(view_pager)
    }

    fun tag(msg: Any) {
        android.util.Log.d("tag", "result:" + msg)
    }

    private fun jump2GitHub() {
        var uri = android.net.Uri.parse(com.kotlinmobile.cn.activity.activity.MainActivity.Companion.GITHUB_URL)
        intent = android.content.Intent(android.content.Intent.ACTION_VIEW, uri)
        //intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:15711150582"))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(com.kotlinmobile.cn.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem?): Boolean {
        val id = item?.itemId
        if (id == com.kotlinmobile.cn.R.id.action_abut) {
            intent = android.content.Intent(this, AboutActivity().javaClass)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
