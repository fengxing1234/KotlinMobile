package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.getHtml
import com.kotlinmobile.cn.model.DetailInfo
import com.kotlinmobile.cn.model.ListDetail
import com.kotlinmobile.cn.model.Page
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/8.
 */
class ListDetailSource() : Source<ListDetail> {
    override fun obtain(url: String): ListDetail {
        var list = ArrayList<Page>()
        var html = getHtml(url)
        var document = Jsoup.parse(html)
        var elements = document.select("div.volumeControl").select("a")
        for (element in elements) {
            var title = element.text()
            var link = "http://ishuhui.net" + element.attr("href")
            var page = Page(title, link)
            list.add(page)
        }
        var description = document.select("div.mangaInfoTextare").text()
        var updateTime = document.select("div.mangaInfoDate").text()
        var info = DetailInfo(description, updateTime)
        return ListDetail(list, info)
    }

}