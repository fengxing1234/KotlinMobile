package com.kotlinmobile.cn.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.model.Page
import kotlinx.android.synthetic.main.item_page.view.*

/**
 * Created by fengxing on 2017/6/8.
 */
class PageAdapter(var mData: List<Page> = ArrayList<Page>(), val itemClick: (View, Int) -> Unit) : RecyclerView.Adapter<PageAdapter.PageHolder>() {
    override fun onBindViewHolder(p0: PageHolder, p1: Int) {
        p0.itemView.tv_page.text = mData[p1].title
        p0.itemView.tv_page.setOnClickListener { itemClick(p0.itemView, p1) }
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): PageHolder {
        var inflate = LayoutInflater.from(p0?.context).inflate(R.layout.item_page, p0, false)
        return PageHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun refreshData(data: List<Page>) {
        mData = data
        notifyDataSetChanged()
    }

    class PageHolder(view: View) : RecyclerView.ViewHolder(view) {}
}