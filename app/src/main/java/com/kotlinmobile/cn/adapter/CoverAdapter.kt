package com.kotlinmobile.cn.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinmobile.cn.R
import com.kotlinmobile.cn.model.Cover
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cover.view.*

/**
 * Created by fengxing on 2017/5/31.
 */
class CoverAdapter(var data: List<Cover> = ArrayList(), var itemClick: (View, Int) -> Unit)
    : RecyclerView.Adapter<CoverAdapter.CoverAdapterHolder>() {

    override fun onBindViewHolder(p0: CoverAdapterHolder, p1: Int) {
        bindView(p0.itemView,p1)
    }

    private fun bindView(itemView: View, position: Int) {
        var cover = data.get(position)
        itemView.tv_cover.text = cover.title
        //设置图片
        Picasso.with(itemView.context).load(cover.coverUrl).into(itemView.iv_cover)
        itemView.ll_content.setOnClickListener{itemClick(itemView,position)}
    }

    fun getData(newData:List<Cover>){
        data = newData
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): CoverAdapterHolder {
        var view = LayoutInflater.from(p0?.context).inflate(R.layout.item_cover, p0, false)
        return CoverAdapterHolder(view)
    }

    override fun getItemCount(): Int = data.size


    class CoverAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
