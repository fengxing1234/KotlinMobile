package com.kotlinmobile.cn

import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.webkit.WebView
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
fun Any.log(msg:String){
    Log.d(this.javaClass.simpleName,msg)
}
fun View.snackbar(messageRes : String, duration: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this,messageRes,duration).show()
}
fun WebView.load(html: String) {
    this.loadDataWithBaseURL("http://ishuhui.net/", html, "text/html", "charset=utf-8", null)
}
