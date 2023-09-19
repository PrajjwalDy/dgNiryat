package com.hindu.dgniryat.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hindu.dgniryat.Callback.IConsignmentCallback
import com.hindu.dgniryat.Model.ArticleModel

class DashboardViewModel : ViewModel() {

    private var articleLiveData:MutableLiveData<List<ArticleModel>>? = null
    //private val articleCallback:IConsignmentCallback = this
    private var messageError:MutableLiveData<String>? = null


    //val articleModel:MutableLiveData<Li>

}