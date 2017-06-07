package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.getHtml
import com.kotlinmobile.cn.model.Comic
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/7.
 */
class ComicSource : Source<ArrayList<Comic>> {
    override fun obtain(url: String): ArrayList<Comic> {
        var list = ArrayList<Comic>()
        var html = getHtml(url)
        var document = Jsoup.parse(html)
        var elements = document.select("div.mangaContentMainImg").select("img")
        for (element in elements) {
            var comicUrl: String
            var attr = element.attr("src")
            if (attr.contains(".jpg") || attr.contains(".png") || attr.contains("JPEG")) {
                comicUrl = attr
            } else if (!"".equals(element.attr("data-original"))) {
                comicUrl = element.attr("data-original")
            } else {
                continue
            }
            var comic = Comic(comicUrl)
            list.add(comic)
        }

        return list
    }

}