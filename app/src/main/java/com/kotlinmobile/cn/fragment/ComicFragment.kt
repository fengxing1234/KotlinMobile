package com.kotlinmobile.cn.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.log
import com.kotlinmobile.cn.snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by fengxing on 2017/6/7.
 */
class ComicFragment(val url: String) : Fragment() {

    lateinit var iv_comic: ImageView
    lateinit var pb_comic: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(url)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_comic, container, false)
        iv_comic = rootView.find(R.id.iv_comic)
        pb_comic = rootView.find(R.id.pb_comic)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        pb_comic.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.material_deep_purple_300)
                .into(iv_comic, object : Callback {
                    override fun onSuccess() {
                        pb_comic.visibility = View.GONE
                    }

                    override fun onError() {
                        iv_comic.snackbar("加载错误请从新加载")
                    }

                })
    }
}