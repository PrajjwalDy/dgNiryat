package com.hindu.dgniryat.ui.mainscreen.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.callback.IConsignmentCallback
import com.hindu.dgniryat.model.ArticleModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel(), IConsignmentCallback {

    private var articleLiveData:MutableLiveData<List<ArticleModel>>? = null
    private val articleCallback:IConsignmentCallback = this
    private var messageError:MutableLiveData<String>? = null


    val articleModel:MutableLiveData<List<ArticleModel>>?
        get() {
            if (articleLiveData == null){
                articleLiveData = MutableLiveData()
                messageError = MutableLiveData()
                CoroutineScope(Dispatchers.IO).launch {
                    loadData()
                }
            }
            return articleLiveData
        }

    private fun loadData() {
        val articleList = ArrayList<ArticleModel>()
        val data = FirebaseDatabase.getInstance().reference
            .child("Consignments")

        data.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                articleList.clear()
                for (snapshot in snapshot.children){
                    val data = snapshot.getValue(ArticleModel::class.java)
                    if (data!!.publisher == FirebaseAuth.getInstance().currentUser!!.uid){
                        articleList.add(data!!)

                    }
                }
                articleCallback.onConsignmentLoadSuccess(articleList)
            }

            override fun onCancelled(error: DatabaseError) {
                articleCallback.onConsignmentLoadFailed(error.message)
            }

        })
    }

    override fun onConsignmentLoadFailed(str: String) {
        val mutableLiveData = messageError
        mutableLiveData!!.value = str
    }

    override fun onConsignmentLoadSuccess(list: List<ArticleModel>) {
        val mutableLiveData = articleLiveData
        mutableLiveData!!.value = list
    }

}