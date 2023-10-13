package com.hindu.dgniryat.ui.otherscreen.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.hindu.dgniryat.model.ArticleModel
import com.hindu.dgniryat.R

class ArticleDetails : Fragment() {

    private lateinit var orderId:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_article_details, container, false)

        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (pref != null){
            this.orderId = pref.getString("orderId","none")!!
        }

        val imageView = root.findViewById<ImageView>(R.id.barcodeImage)
        val articleName = root.findViewById<TextView>(R.id.articleName_Details)
        val orderNo = root.findViewById<TextView>(R.id.orderNo_details)
        val productType = root.findViewById<TextView>(R.id.productType_details)
        val typeCount = root.findViewById<TextView>(R.id.no_of_types_detils)
        val nonDelivery = root.findViewById<TextView>(R.id.non_delivery_details)
        val country = root.findViewById<TextView>(R.id.toCountry_details)
        val itemCategory = root.findViewById<TextView>(R.id.itemCategory_details)


        setBitmap(imageView,orderId)

        orderNo.text = orderId

        getData(orderId,country,articleName,nonDelivery,typeCount,productType,itemCategory)

        return root
    }

    private fun generateBarcode(data: String, width: Int, height: Int): Bitmap? {
        try {
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                data,
                BarcodeFormat.CODE_128,
                width,
                height
            )

            val barcodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    barcodeBitmap.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    )
                }
            }
            return barcodeBitmap
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun setBitmap(imageView: ImageView, barcodeData: String) {
        val barcodeWidth = 800
        val barcodeHeight = 200

        val barcodeBitmap = generateBarcode(barcodeData, barcodeWidth, barcodeHeight)

        if (barcodeBitmap != null) {
            imageView.setImageBitmap((barcodeBitmap))
        }
    }


    private fun getData(orderId: String,country:TextView,articleName:TextView,nonDelivery:TextView,typeCount:TextView,productType:TextView,itemCategory:TextView) {
        val dbRef = FirebaseDatabase.getInstance()
            .reference
            .child("Consignments")
            .child(orderId)

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val data = snapshot.getValue(ArticleModel::class.java)

                    articleName.text = data?.orderName
                    country.text = data?.rCountry
                    nonDelivery.text = data?.nonDelivery
                    typeCount.text = data?.typeCount
                    productType.text = data?.productType
                    itemCategory.text = data?.itemCategory


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}