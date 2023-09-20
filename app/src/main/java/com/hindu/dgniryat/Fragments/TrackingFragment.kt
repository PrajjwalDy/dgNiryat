package com.hindu.dgniryat.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.Model.ArticleModel
import com.hindu.dgniryat.Model.StatusModel
import com.hindu.dgniryat.R

class TrackingFragment : Fragment() {

    private lateinit var orderId:String
    private lateinit var orderName:String
    private lateinit var message_up:TextView
    private lateinit var messgage_sw:TextView
    private lateinit var messgage_ic:TextView
    private lateinit var messgage_iw:TextView
    private lateinit var messgage_ec:TextView
    private lateinit var messgage_ew:TextView
    private lateinit var messgage_rl:TextView

    private lateinit var text_up:TextView
    private lateinit var text_dg:TextView
    private lateinit var text_ew:TextView
    private lateinit var text_ec:TextView
    private lateinit var text_sw:TextView
    private lateinit var text_ic:TextView
    private lateinit var text_iw:TextView
    private lateinit var text_rl:TextView
    private lateinit var text_dl:TextView

    //ImageView
    private lateinit var image_up:ImageView
    private lateinit var image_dg:ImageView
    private lateinit var image_dl:ImageView
    private lateinit var image_ec:ImageView
    private lateinit var image_ew:ImageView
    private lateinit var image_ic:ImageView
    private lateinit var image_iw:ImageView
    private lateinit var image_rl:ImageView
    private lateinit var image_sw:ImageView


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

        message_up = root.findViewById<TextView>(R.id.message_dp_dakghar)
        messgage_sw = root.findViewById<TextView>(R.id.message_shipWarehouse)
        messgage_ic = root.findViewById<TextView>(R.id.message_importCustoms)
        messgage_iw = root.findViewById<TextView>(R.id.message_import_warehouse)
        messgage_rl = root.findViewById<TextView>(R.id.message_reg_logistics)
        messgage_ec = root.findViewById<TextView>(R.id.message_exportCustom)
        messgage_ew = root.findViewById<TextView>(R.id.message_exportWarehouse)

        //TextView
        text_dg = root.findViewById<TextView>(R.id.dp_dakghar)
        text_ew = root.findViewById<TextView>(R.id.exportWarehouse)
        text_ec = root.findViewById<TextView>(R.id.exportCustom)
        text_sw = root.findViewById<TextView>(R.id.ship_warehouse)
        text_ic = root.findViewById<TextView>(R.id.import_custom)
        text_iw = root.findViewById<TextView>(R.id.import_warehouse)
        text_rl = root.findViewById(R.id.import_warehouse)
        text_dl = root.findViewById(R.id.delivered)

        //Images
        image_up = root.findViewById<ImageView>(R.id.img_underProcess)
        image_dg = root.findViewById<ImageView>(R.id.img_dp_dakghar)
        image_ew = root.findViewById<ImageView>(R.id.img_export_warehouse)
        image_ec = root.findViewById<ImageView>(R.id.img_export_custom)
        image_sw = root.findViewById<ImageView>(R.id.img_ship_warehouse)
        image_ic = root.findViewById<ImageView>(R.id.img_import_custom)
        image_iw = root.findViewById<ImageView>(R.id.img_import_warehouse)
        image_rl = root.findViewById<ImageView>(R.id.img_regional_logistic)
        image_dl = root.findViewById<ImageView>(R.id.img_delivered)


        orderNo.text = "Order ID:"+orderId
        articleName.text = orderName

        details.setOnClickListener {
            val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
            pref?.putString("orderId",orderId)
            pref?.apply()
            Navigation.findNavController(root).navigate(R.id.action_trackingFragment_to_articleDetails)
        }

        updateStatus()




        return root
    }

    private fun updateStatus(){
        val dbRef = FirebaseDatabase.getInstance().reference.child("Status")
            .child(orderId)

        val blue = ContextCompat.getColor(requireContext(), R.color.blue)

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val data = snapshot.getValue(StatusModel::class.java)
                    if (data?.underProcess ==1){
                        image_up.setImageResource(R.drawable.process_approved)
                    }else if (data?.underProcess==2){
                        image_up.setImageResource(R.drawable.process_rejected)
                    }



                    if (data?.dispatchDakghar==1){
                        image_dg.setImageResource(R.drawable.process_approved)
                        text_dg.setTextColor(blue)
                    }else if (data?.dispatchDakghar==2){
                        image_dg.setImageResource(R.drawable.process_rejected)
                        message_up.setTextColor(Color.RED)
                    }

                    message_up.text = data?.message_dg


                    if (data?.reachedExportWH==1){
                        image_ew.setImageResource(R.drawable.process_approved)
                        text_ew.setTextColor(blue)
                    }else if (data?.reachedExportWH==2){
                        image_ew.setImageResource(R.drawable.process_rejected)
                        messgage_ew.setTextColor(Color.RED)
                    }

                    messgage_ew.text = data?.message_ew

                    if (data?.exportCustom==1){
                        image_ec.setImageResource(R.drawable.process_approved)
                        text_ec.setTextColor(blue)
                    }else if (data?.exportCustom==2){
                        image_ec.setImageResource(R.drawable.process_rejected)
                        messgage_ec.setTextColor(Color.RED)
                    }

                    messgage_ec.text = data?.message_ec

                    if (data?.shipWarehouse==1){
                        image_sw.setImageResource(R.drawable.process_approved)
                        text_sw.setTextColor(blue)
                    }else if (data?.shipWarehouse==2){
                        image_sw.setImageResource(R.drawable.process_rejected)
                        messgage_sw.setTextColor(Color.RED)
                    }

                    messgage_sw.text = data?.message_sw

                    if (data?.importCustom==1){
                        image_ic.setImageResource(R.drawable.process_approved)
                        text_ic.setTextColor(blue)
                    }else if (data?.importCustom==2){
                        image_ic.setImageResource(R.drawable.process_rejected)
                        messgage_ic.setTextColor(Color.RED)
                    }

                    messgage_ic.text = data?.message_ic

                    if (data?.importWarehouse==1){
                        image_iw.setImageResource(R.drawable.process_approved)
                        text_iw.setTextColor(blue)
                    }else if (data?.importWarehouse==2){
                        image_iw.setImageResource(R.drawable.process_rejected)
                        messgage_sw.setTextColor(Color.RED)
                    }

                    messgage_iw.text = data?.message_iw

                    if (data?.regionalLogistics==1){
                        image_rl.setImageResource(R.drawable.process_approved)
                        text_rl.setTextColor(blue)
                    }else if (data?.regionalLogistics==2){
                        image_rl.setImageResource(R.drawable.process_rejected)
                        messgage_rl.setTextColor(Color.RED)
                    }

                    messgage_rl.text = data?.message_rl


                    if (data?.delivered==1){
                        image_dl.setImageResource(R.drawable.delivered_filled)
                        text_dl.setTextColor(blue)
                    }else if (data?.delivered==2){
                        image_dl.setImageResource(R.drawable.process_rejected)
                    }



                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}