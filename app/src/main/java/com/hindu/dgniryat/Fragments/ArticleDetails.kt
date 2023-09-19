package com.hindu.dgniryat.Fragments

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
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.hindu.dgniryat.Model.ArticleModel
import com.hindu.dgniryat.R

class ArticleDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_article_details, container, false)

        val imageView = root.findViewById<ImageView>(R.id.barcodeImage)
        val articleName = root.findViewById<TextView>(R.id.articleName_Details)
        val orderNo = root.findViewById<TextView>(R.id.orderNo_details)
        val productType = root.findViewById<TextView>(R.id.productType_details)
        val typeCount = root.findViewById<TextView>(R.id.no_of_types_detils)
        val nonDelivery = root.findViewById<TextView>(R.id.non_delivery_details)


        generateBarcode()

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
        val barcodeWidth = 400
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