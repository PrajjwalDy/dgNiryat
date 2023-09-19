package com.hindu.dgniryat.Callback

import com.hindu.dgniryat.Model.ArticleModel

interface IConsignmentCallback {
    fun onConsignmentLoadFailed(str:String)
    fun onConsignmentLoadSuccess(list: List<ArticleModel>)
}