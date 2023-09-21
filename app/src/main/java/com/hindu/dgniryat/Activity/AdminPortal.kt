package com.hindu.dgniryat.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.withStarted
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.hindu.dgniryat.Fragments.ArticleDetails
import com.hindu.dgniryat.Model.ArticleModel
import com.hindu.dgniryat.R

class AdminPortal : AppCompatActivity() {

    private lateinit var scanImage:ImageView

    private var requestCamera:ActivityResultLauncher<String>? = null
    private lateinit var orderSearch:EditText
    private lateinit var searchBtn: AppCompatButton

    private lateinit var orderIdTV:TextView
    private lateinit var toCounty:TextView
    private lateinit var productType:TextView
    private lateinit var itemTypeCount:TextView
    private lateinit var itemCategory:TextView
    private lateinit var nonDelivery:TextView
    private lateinit var totalWeight:TextView
    private lateinit var paymentStatus:TextView
    private lateinit var level:Spinner
    private lateinit var reject:AppCompatButton
    private lateinit var approve:AppCompatButton
    private lateinit var details_ll:ScrollView

    private val SMS_PERMISSION_REQUEST_CODE = 123

    private var orderId = ""
    var selectedLevel = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_portal)

        val intent = intent
        orderId = intent.getStringExtra("orderId").toString()

        scanImage = findViewById(R.id.searchIcon)
        orderSearch = findViewById(R.id.orderId_editText)
        searchBtn = findViewById(R.id.search_new)

        orderIdTV = findViewById(R.id.orderId_admin)
        toCounty = findViewById(R.id.toCountry_admin)
        productType = findViewById(R.id.productType_admin)
        itemTypeCount = findViewById(R.id.no_of_types_admin)
        itemCategory = findViewById(R.id.itemCategory_admin)
        nonDelivery = findViewById(R.id.non_delivery_admin)
        totalWeight = findViewById(R.id.totalWeight)
        paymentStatus = findViewById(R.id.paymentStatus)
        level = findViewById(R.id.selectLevel)
        reject = findViewById(R.id.reject)
        approve = findViewById(R.id.approve)
        details_ll = findViewById(R.id.articleDetails_admin_ll)




        requestCamera = registerForActivityResult(ActivityResultContracts.RequestPermission(),){
            if (it){
                startActivity(Intent(this,BarcodeScan::class.java))

            }else{
                Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show()
            }
        }

        scanImage.setOnClickListener {
            requestCamera?.launch(android.Manifest.permission.CAMERA)
        }
        orderSearch.setText(orderId)

        if (orderId != null){
            loadData()
        }

        //Select Product
        val products = resources.getStringArray(R.array.Level)

        if (level != null) {
            val adapter = ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                products
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            level.adapter = adapter
        }


        level.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = products[p2]
                selectedLevel = selectedItem

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        approve.setOnClickListener {
        if (selectedLevel == "Select Level"){
            Toast.makeText(this,"Please select level",Toast.LENGTH_SHORT).show()
        }else{
            approve()
        }
        }

        reject.setOnClickListener {
            if (selectedLevel == "Select Level"){
                Toast.makeText(this,"Please select level",Toast.LENGTH_SHORT).show()
            }else{
                reject()
            }
        }

    }

    private fun loadData(){
        val database = FirebaseDatabase.getInstance()
            .reference.child("Consignments")
            .child(orderId)

        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    details_ll.visibility = View.VISIBLE
                    val data = snapshot.getValue(ArticleModel::class.java)
                    orderIdTV.text = data?.orderId
                    toCounty.text = data?.rCountry
                    productType.text = data?.productType
                    itemTypeCount.text = data?.typeCount
                    itemCategory.text = data?.itemCategory
                    totalWeight.text = data?.grossWeight
                    nonDelivery.text = data?.nonDelivery
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun approve(){
        val db = FirebaseDatabase.getInstance().reference.child("Status")
            .child(orderId)

        val statusMap = HashMap<String,Any>()

        if (selectedLevel == "Dakghar"){
            statusMap["dispatchDakghar"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Export Warehouse"){
            statusMap["reachedExportWH"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Export Custom"){
            statusMap["exportCustom"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Ship Warehouse"){
            statusMap["exportCustom"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Import Custom"){
            statusMap["importCustom"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Import Warehouse"){
            statusMap["importWarehouse"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Regional Logistics"){
            statusMap["regionalLogistics"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Delivered"){
            statusMap["delivered"] = 1
            db.updateChildren(statusMap)
        }
        Toast.makeText(this,"Consignment Approved",Toast.LENGTH_SHORT).show()
    }

    private fun reject(){
        val db = FirebaseDatabase.getInstance().reference.child("Status")
            .child(orderId)

        val statusMap = HashMap<String,Any>()

        if (selectedLevel == "Dakghar"){
            statusMap["dispatchDakghar"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Export Warehouse"){
            statusMap["reachedExportWH"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Export Custom"){
            statusMap["exportCustom"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Ship Warehouse"){
            statusMap["exportCustom"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Import Custom"){
            statusMap["importCustom"] = 1
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Import Warehouse"){
            statusMap["importWarehouse"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Regional Logistics"){
            statusMap["regionalLogistics"] = 2
            db.updateChildren(statusMap)
        }

        if (selectedLevel == "Delivered"){
            statusMap["delivered"] = 2
            db.updateChildren(statusMap)
        }
        Toast.makeText(this,"Consignment Rejected",Toast.LENGTH_SHORT).show()
    }
}