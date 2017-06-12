package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.getHtml
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/12.
 */
class NewsDetailSource : Source<String>{
    override fun obtain(url: String): String {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val contentHtml =
                "<html>${doc.select("head").toString()}<body>${doc.select("div.featureContentText").toString()}</body></html>"
        return contentHtml
    }
}