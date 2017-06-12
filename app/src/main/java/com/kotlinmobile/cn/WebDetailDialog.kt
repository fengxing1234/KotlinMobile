package com.kotlinmobile.cn

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomSheetDialog
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.webkit.WebSettings
import android.webkit.WebView
import com.kotlinmobile.cn.model.NewsContent
import com.kotlinmobile.cn.network.NewsDetailSource
import com.kotlinmobile.cn.network.Source
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * Created by fengxing on 2017/6/12.
 */
class WebDetailDialog(val context: Context, val data: NewsContent ,val source: Source<String>) {
    val dialog = BottomSheetDialog(context)

    init {
        var inflate = LayoutInflater.from(context).inflate(R.layout.web_detail_dialog, null)
        var toolbar = inflate.find<Toolbar>(R.id.titleBar)
        var refresh = inflate.find<SwipeRefreshLayout>(R.id.newsDetailRefresh)
        var webView = inflate.find<WebView>(R.id.webView)

       toolbar.subtitle = data.title

        webView.settings.textZoom = 80
        webView.settings.displayZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN


        dialog.setContentView(inflate)

        refresh.post { refresh.isRefreshing = true }


        async() {
            val html = NewsDetailSource().obtain(data.url)
            uiThread {
                webView.load(html)
                refresh.isRefreshing = false
            }
        }

        refresh.setOnRefreshListener {
            async() {
                val html = source.obtain(data.url)
                uiThread {
                    webView.load(html)
                    refresh.isRefreshing = false
                }
            }
        }

        dialog.show()
    }

}