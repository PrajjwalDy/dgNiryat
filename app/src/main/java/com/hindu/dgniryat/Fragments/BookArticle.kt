package com.hindu.dgniryat.Fragments

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hindu.dgniryat.R
import java.io.IOException

class BookArticle : Fragment() {

    private lateinit var orderName: EditText
    private lateinit var typeCount: EditText
    private lateinit var weight: EditText
    private lateinit var certificateCount: EditText
    private lateinit var licCount: EditText
    private lateinit var invCount: EditText
    private lateinit var receiverName: EditText
    private lateinit var receiverEmail: EditText
    private lateinit var receiverPhone: EditText
    private lateinit var city: EditText
    private lateinit var addressLine2: EditText
    private lateinit var addressLine1: EditText
    private lateinit var residence: EditText

    //Spinner Data Update
    var selectedCountry = ""
    var selectedProductType =""
    var selectedCategory =""
    var selectedReturn = ""
    var selectedPbe = ""

    //Payment
    private lateinit var totalAmount:TextView
    private lateinit var countryCharge:TextView
    private lateinit var weightCharge:TextView
    private lateinit var gstCharge:TextView

    private val SMS_PERMISSION_REQUEST_CODE = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_book_article, container, false)

        //Buttons
        val proceedbtn1 = root.findViewById<AppCompatButton>(R.id.proceedBtn1)
        val proceedbtn2 = root.findViewById<AppCompatButton>(R.id.proceedBtn2)

        //Layout
        val articleDetails = root.findViewById<ScrollView>(R.id.LL_articleDetails)
        val receiverDetails = root.findViewById<ScrollView>(R.id.ll_receiverDetails)
        val paymentCardView = root.findViewById<CardView>(R.id.paymentCV)

        //objects
        orderName = root.findViewById<EditText>(R.id.ariticleName)
        typeCount = root.findViewById<EditText>(R.id.itemCount)
        weight = root.findViewById<EditText>(R.id.grossWeight)
        licCount = root.findViewById<EditText>(R.id.lic_count)
        invCount = root.findViewById<EditText>(R.id.invoiceCount)
        certificateCount = root.findViewById<EditText>(R.id.certificate)

        //Receiver Details
        receiverName = root.findViewById<EditText>(R.id.receiverName)
        residence = root.findViewById<EditText>(R.id.companyName)
        addressLine1 = root.findViewById<EditText>(R.id.addressLine1)
        addressLine2 = root.findViewById<EditText>(R.id.addressLine2)
        city = root.findViewById<EditText>(R.id.city)
        receiverPhone = root.findViewById<EditText>(R.id.receiverPhone)
        receiverEmail = root.findViewById<EditText>(R.id.receiverEmail)
        val receiverCountry = root.findViewById<EditText>(R.id.receiver_country)



        //Spinners
        val selectCountry = root.findViewById<Spinner>(R.id.country_spinner)
        val productType = root.findViewById<Spinner>(R.id.productType)
        val itemCategory = root.findViewById<Spinner>(R.id.itemCategory)
        val nonDelivery = root.findViewById<Spinner>(R.id.non_delivery)
        val pbe = root.findViewById<Spinner>(R.id.selectPBE)


        //Payment
        totalAmount = root.findViewById(R.id.amount)
        countryCharge = root.findViewById(R.id.countryCharge_amount)
        weightCharge = root.findViewById(R.id.weightCharge_amount)
        gstCharge = root.findViewById(R.id.gstCharge_amount)

        val payButton =root.findViewById<AppCompatButton>(R.id.confirmPay)



        //Select Country
        val countries = resources.getStringArray(R.array.Country)
        if (selectCountry != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                countries
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            selectCountry.adapter = adapter
        }

        selectCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = countries[p2]
                selectedCountry = selectedItem
                receiverCountry.setText(selectedItem)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //Select Product
        val products = resources.getStringArray(R.array.Product)

        if (productType != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                products
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            productType.adapter = adapter
        }


        productType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = products[p2]
                selectedProductType = selectedItem

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //Select Item Category
        val items = resources.getStringArray(R.array.ItemCategory)
        if (itemCategory != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                items
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            itemCategory.adapter = adapter
        }

        itemCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = products[p2]
                selectedCategory = selectedItem
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //Select Non Delivery
        val nondeliveries = resources.getStringArray(R.array.NonDelivery)
        if (nonDelivery != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                nondeliveries
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            nonDelivery.adapter = adapter
        }

        nonDelivery.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = nondeliveries[p2]
                selectedReturn = selectedItem
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //Select PBE
        val Pbe = resources.getStringArray(R.array.PBE)
        if (pbe != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                Pbe
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            pbe.adapter = adapter
        }

        pbe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = nondeliveries[p2]
                selectedPbe = selectedItem
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        proceedbtn1.setOnClickListener {
            articleDetails.visibility = View.GONE
            receiverDetails.visibility = View.VISIBLE
        }

        proceedbtn2.setOnClickListener {
            receiverDetails.visibility = View.GONE
            paymentCardView.visibility = View.VISIBLE
            calculatePostage()
        }




        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.SEND_SMS) !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.SEND_SMS),
                SMS_PERMISSION_REQUEST_CODE
            )

        }else{



        }


        payButton.setOnClickListener {
            saveData(root)
        }


        return root
    }


    private fun saveData(view: View) {
        Snackbar.make(view, "Adding article....", Snackbar.LENGTH_SHORT).show()
        val dbRef = FirebaseDatabase.getInstance().reference.child("Consignments")
        val orderId = dbRef.push().key.toString()

        val orderMap = HashMap<String, Any>()
        orderMap["orderId"] = orderId
        orderMap["orderName"] = orderName.text.toString()
        orderMap["rCountry"] = selectedCountry
        orderMap["productType"] =selectedProductType
        orderMap["itemCategory"] = selectedCategory
        orderMap["typeCount"] = typeCount.text.toString()
        orderMap["grossWeight"] = weight.text.toString()
        orderMap["nonDelivery"] = selectedReturn
        orderMap["LicCont"] = licCount.text.toString()
        orderMap["InvCount"] = invCount.text.toString()
        orderMap["certificateCount"] = certificateCount.text.toString()
        orderMap["pbe"] = selectedPbe
        orderMap["publisher"] = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef.child(orderId).updateChildren(orderMap)
        setStatus(orderId)
        receiverData(view,orderId)
        val message = "Dear user your Article is booked for $selectedCategory with ORDER NO. $orderId, Thank you for choosing Dakghar Niryat Kendra"
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.SEND_SMS) !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.SEND_SMS),
                SMS_PERMISSION_REQUEST_CODE
            )

        }else{
            sendSms(message)

        }
        Navigation.findNavController(view).navigate(R.id.action_bookArticle_to_navigation_dashboard)
        Toast.makeText(context,"Payment Success & Article Added", Toast.LENGTH_SHORT).show()
    }

    private fun receiverData(view:View, orderId: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Address")

        val orderMap = HashMap<String, Any>()
        orderMap["receiverName"] = receiverName.text.toString()
        orderMap["receiverAddress"] = "${addressLine1.text} +${addressLine2.text}"
        orderMap["country"] = selectedCountry
        orderMap["city"] = city.text.toString()
        orderMap["rPhone"] = receiverPhone.text.toString()
        orderMap["rEmail"] = receiverEmail.text.toString()


        dbRef.child(orderId).updateChildren(orderMap)
        Snackbar.make(view, "Article added successfully", Snackbar.LENGTH_SHORT).show()

    }

    private fun setStatus(orderId:String){
        val dbRef = FirebaseDatabase.getInstance().reference.child("Status")

        val statusMap = HashMap<String,Any>()
        statusMap["underProcess"] = 1
        statusMap["dispatchDakghar"] =0
        statusMap["reachedExportWH"] =0
        statusMap["exportCustom"] =0
        statusMap["shipWarehouse"] =0
        statusMap["importCustom"] =0
        statusMap["importWarehouse"] =0
        statusMap["regionalLogistics"] =0
        statusMap["delivered"] =0

        statusMap["message_dg"] = ""
        statusMap["message_ew"] = ""
        statusMap["message_ec"] = ""
        statusMap["message_sw"] = ""
        statusMap["message_ic"] = ""
        statusMap["message_iw"] = ""
        statusMap["message_rl"] = ""



        dbRef.child(orderId).updateChildren(statusMap)

    }

    private fun calculatePostage(){
        var cCharge = 0
        var totalCharge =0
        var weightamount = weight.text.toString().toInt()
        var weightCharge = (weightamount/1000)*750
        var gst = 0


        if (selectedCountry == "USA"){
            cCharge = 50000
            var grossAmount = cCharge+weightCharge
            gst = grossAmount*18/100
            totalCharge = gst+grossAmount

            totalAmount.text = totalCharge.toString()
            gstCharge.text = gst.toString()
            countryCharge.text = cCharge.toString()

        }

        if (selectedCountry =="England"){
            cCharge = 30000
            var grossAmount = cCharge+weightCharge
            gst = grossAmount*18/100
            totalCharge = gst+grossAmount

            totalAmount.text = totalCharge.toString()
            gstCharge.text = gst.toString()
            countryCharge.text = cCharge.toString()
        }

        if (selectedCountry =="France"){
            cCharge = 45000
            var grossAmount = cCharge+weightCharge
            gst = grossAmount*18/100
            totalCharge = gst+grossAmount

            totalAmount.text = totalCharge.toString()
            gstCharge.text = gst.toString()
            countryCharge.text = cCharge.toString()
        }

        if (selectedCountry =="Russia"){
            cCharge = 20000
            var grossAmount = cCharge+weightCharge
            gst = grossAmount*18/100
            totalCharge = gst+grossAmount

            totalAmount.text = totalCharge.toString()
            gstCharge.text = gst.toString()
            countryCharge.text = cCharge.toString()
        }

        if (selectedCountry =="Japan"){
            cCharge = 25000
            var grossAmount = cCharge+weightCharge
            gst = grossAmount*18/100
            totalCharge = gst+grossAmount

            totalAmount.text = totalCharge.toString()
            gstCharge.text = gst.toString()
            countryCharge.text = cCharge.toString()
        }


    }

    private fun sendSms(message:String){
        val smsManager = SmsManager.getDefault()
        val sentIntent = Intent("SMS_SENT")
        val deliveredIntent = Intent("SMS_DELIVERED")

        val sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE)
        val deliveredPI = PendingIntent.getBroadcast(context, 0, deliveredIntent,
            PendingIntent.FLAG_IMMUTABLE)


        try {
            smsManager.sendTextMessage("9118847494", "7460802449", message, sentPI, deliveredPI)
        }catch (e:IOException){
            e.printStackTrace()

        }
    }
}