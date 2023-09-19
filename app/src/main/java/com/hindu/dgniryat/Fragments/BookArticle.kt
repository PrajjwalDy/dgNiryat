package com.hindu.dgniryat.Fragments

import android.content.BroadcastReceiver
import android.os.Bundle
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
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.hindu.dgniryat.R

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

        dbRef.child(orderId).updateChildren(orderMap)
        receiverData(view,orderId)
    }

    private fun receiverData(view:View, orderId: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Consignments")

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
}