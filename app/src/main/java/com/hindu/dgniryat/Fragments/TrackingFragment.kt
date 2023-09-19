package com.hindu.dgniryat.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.Model.ArticleModel
import com.hindu.dgniryat.R

class TrackingFragment : Fragment() {

    private lateinit var orderId:String
    private lateinit var orderName:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root:View = inflater.inflate(R.layout.fragment_tracking, container, false)

        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (pref != null){
            this.orderId = pref.getString("orderId","none")!!
            this.orderName = pref.getString("articleName","none")!!
        }


        val details = root.findViewById<TextView>(R.id.detailsTV)
        val articleName = root.findViewById<TextView>(R.id.articleName_track)
        val orderNo = root.findViewById<TextView>(R.id.orderNo_track)

        orderNo.text = orderId
        articleName.text = orderName

        details.setOnClickListener {
            val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
            pref?.putString("orderId",orderId)
            pref?.apply()
            Navigation.findNavController(root).navigate(R.id.action_trackingFragment_to_articleDetails)
        }




        return root
    }

}