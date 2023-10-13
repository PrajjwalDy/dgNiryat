package com.hindu.dgniryat.callback

import com.hindu.dgniryat.model.ArticleModel

interface IConsignmentCallback {
    fun onConsignmentLoadFailed(str:String)
    fun onConsignmentLoadSuccess(list: List<ArticleModel>)
}