package com.kotlinmobile.cn.model

/**
 * Created by fengxing on 2017/5/31.
 */
data class Cover(val coverUrl: String, val title: String, val link: String)

data class Comic(val url: String)
data class Page(val title: String, val url: String)
data class DetailInfo(val description: String, val updateTime: String)
data class ListDetail(val pages: List<Page>, val detail: DetailInfo) {
    operator fun get(position: Int) = pages[position]
    fun size() = pages.size
}

data class NewsList(val title: String, val link: String, val subTitle: String, val newsContent: ArrayList<NewsContent>)
data class NewsContent(val url: String, val title: String, val time: String)