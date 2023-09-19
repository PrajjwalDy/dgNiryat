package com.hindu.dgniryat.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val message_up = root.findViewById<TextView>(R.id.message_dp_dakghar)
        val messgage_sw = root.findViewById<TextView>(R.id.ship_warehouse)
        val messgage_ic = root.findViewById<TextView>(R.id.message_importCustoms)
        val messgage_iw = root.findViewById<TextView>(R.id.message_import_warehouse)
        val messgage_rl = root.findViewById<TextView>(R.id.message_reg_logistics)
        val messgage_ec = root.findViewById<TextView>(R.id.message_exportCustom)
        val messgage_ew = root.findViewById<TextView>(R.id.message_exportWarehouse)

        //Images
        val image_up = root.findViewById<ImageView>(R.id.img_underProcess)
        val image_dg = root.findViewById<ImageView>(R.id.img_dp_dakghar)
        val image_ew = root.findViewById<ImageView>(R.id.img_export_warehouse)
        val image_ec = root.findViewById<ImageView>(R.id.img_export_custom)
        val image_sw = root.findViewById<ImageView>(R.id.img_ship_warehouse)
        val image_ic = root.findViewById<ImageView>(R.id.img_import_custom)
        val image_iw = root.findViewById<ImageView>(R.id.img_import_warehouse)
        val image_rl = root.findViewById<ImageView>(R.id.img_regional_logistic)
        val image_dl = root.findViewById<ImageView>(R.id.img_delivered)


        orderNo.text = "Order ID:"+orderId
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