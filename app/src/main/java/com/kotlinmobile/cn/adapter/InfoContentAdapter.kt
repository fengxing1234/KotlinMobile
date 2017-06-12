package com.kotlinmobile.cn.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.model.NewsList

/**
 * Created by fengxing on 2017/6/10.
 */
class InfoContentAdapter(var mData: List<NewsList> = ArrayList<NewsList>()) : RecyclerView.Adapter<InfoContentAdapter.InfoContentViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): InfoContentViewHolder {
        var adapterView = LayoutInflater.from(p0.context).inflate(R.layout.item_news, p0, false)
        return InfoContentViewHolder(adapterView)
    }

    override fun onBindViewHolder(holder: InfoContentViewHolder, position: Int) {
        var itemView = holder.itemView
        var title = itemView.findViewById(R.id.tv_container_title) as TextView
        var more = itemView.findViewById(R.id.tv_more) as TextView
        var container = itemView.findViewById(R.id.rv_child_container) as RecyclerView

        var newsList = mData.get(position)
        title.text = newsList.title
        more.text = newsList.subTitle
        container.layoutManager = LinearLayoutManager(itemView.context)
        var newsContent = newsList.newsContent
        container.adapter = NewsContentAdapter(newsContent)
    }

    override fun getItemCount(): Int = mData.size

    fun getData(data: List<NewsList>) {
        mData = data
        notifyDataSetChanged()
    }

    class InfoContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}



