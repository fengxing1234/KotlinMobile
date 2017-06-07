package com.kotlinmobile.cn.network

import com.squareup.okhttp.OkHttpClient

/**
 * Created by fengxing on 2017/6/6.
 */
object OkClient{
    private val client = OkHttpClient()
    fun instance() = client
}