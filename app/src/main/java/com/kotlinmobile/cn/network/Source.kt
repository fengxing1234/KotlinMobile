package com.kotlinmobile.cn.network

/**
 * Created by fengxing on 2017/6/6.
 */
interface Source<T>{
    fun obtain(url:String):T
}