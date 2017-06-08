package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.getHtml
import com.kotlinmobile.cn.model.Cover
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/8.
 */
class ListCartoonSource : Source<ArrayList<Cover>> {

    override fun obtain(url: String): ArrayList<Cover> {
        var list = ArrayList<Cover>()
        var html = getHtml(url)
        var document = Jsoup.parse(html)
        var elements = document.select("ul.chinaMangaContentList").select("li")
        for (element in elements) {
            var link = "http://ishuhui.net" + element.select("div.chinaMangaContentImg").select("a").attr("href")
            var title = element.select("p").select("a").text()
            var url =
                    if (element.select("img").attr("src").contains("http")) {
                        element.select(("img")).attr("src")
                    } else {
                        "http://ishuhui.net" + element.select("img").attr("src")
                    }
            var cover = Cover(url, title, link)
            list.add(cover)
        }
        return list
    }

}