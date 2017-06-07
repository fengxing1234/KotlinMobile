package com.kotlinmobile.cn

import com.kotlinmobile.cn.network.OkClient
import com.squareup.okhttp.Request

/**
 * Created by fengxing on 2017/6/6.
 */

fun getHtml(url: String): String {

    var okHttpClient = OkClient.instance()
    var reqeust = Request.Builder()
            .url(url)
            .build()
    var response = okHttpClient.newCall(reqeust).execute()
    return response.body().string()
}