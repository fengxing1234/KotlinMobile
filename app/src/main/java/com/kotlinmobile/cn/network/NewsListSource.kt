package com.kotlinmobile.cn.network

import com.kotlinmobile.cn.activity.activity.MainActivity
import com.kotlinmobile.cn.getHtml
import com.kotlinmobile.cn.model.NewsContent
import com.kotlinmobile.cn.model.NewsList
import org.jsoup.Jsoup

/**
 * Created by fengxing on 2017/6/11.
 */
class NewsListSource : Source<List<NewsList>> {
    override fun obtain(url: String): ArrayList<NewsList> {
        var list = ArrayList<NewsList>()
        var html = getHtml(url)
        var parse = Jsoup.parse(html)
        var elements = parse.select("div.reportersMain")
        for (element in elements) {
            var title = element.select("div.mangeListTitle").select("span").text()
            var titleLink = MainActivity.BASE_URL + element.select("div.mangeListTitle").select("a").attr("href")
            var subTitle = element.select("div.mangeListTitle").select("a").text()

            var contentNews = ArrayList<NewsContent>()
            var uls = element.select("ul.reportersList").select("li").select("a")
            for (ul in uls) {
                var newsUrl = MainActivity.BASE_URL + ul.attr("href")
                var newsTitle = ul.select("span")[0].text()
                var newsTime = ul.select("span")[1].text()
                var newsContent = NewsContent(newsUrl, newsTitle, newsTime)
                contentNews.add(newsContent)
            }
            var newsList = NewsList(title,titleLink,subTitle,contentNews)
            list.add(newsList)
        }
        return list
    }

}