package com.kotlinmobile.cn.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.WebDetailDialog
import com.kotlinmobile.cn.model.NewsContent
import com.kotlinmobile.cn.network.NewsDetailSource
import kotlinx.android.synthetic.main.item_news_content.view.*

/**
 * Created by fengxing on 2017/6/12.
 */
class NewsContentAdapter(var data: ArrayList<NewsContent>) : RecyclerView.Adapter<NewsContentAdapter.NewsContentViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(viewHolder: NewsContentViewHolder, position: Int) {
        var itemView = viewHolder.itemView
        var title = itemView.findViewById(R.id.tv_title) as TextView
        var time = itemView.findViewById(R.id.tv_time) as TextView
        var content = data.get(position)
        if(position%2 == 0) itemView.container.setBackgroundResource(R.color.alpha_grey)
        title.text = content.title
        time.text = content.time
        itemView.container.setOnClickListener{
            WebDetailDialog(itemView.context,content,NewsDetailSource())
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, p1: Int): NewsContentViewHolder {
        var rootView = LayoutInflater.from(group.context).inflate(R.layout.item_news_content, group, false)
        return NewsContentViewHolder(rootView)
    }

    override fun getItemCount(): Int = data.size

    class NewsContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
}