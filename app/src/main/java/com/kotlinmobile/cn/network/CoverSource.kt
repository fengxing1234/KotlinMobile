package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.getHtml
import com.kotlinmobile.cn.model.Cover
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/6.
 */
class CoverSource : Source<ArrayList<Cover>> {
    override fun obtain(url: String): ArrayList<Cover> {
        var list = ArrayList<Cover>()

        var html = getHtml(url)
        var document = Jsoup.parse(html)
        var elements = document.select("ul.mangeListBox").select("li")
        for (element in elements) {
            var coverUrl = element.select("img").attr("src")
            var title = element.select("h1").text() + "\n" + element.select("h2").text()
            var link = "http://ishuhui.net" + element.select("div.magesMesBox").select("a").attr("href")
            var cover = Cover(coverUrl, title, link)
            list.add(cover)
        }
        return list
    }

}