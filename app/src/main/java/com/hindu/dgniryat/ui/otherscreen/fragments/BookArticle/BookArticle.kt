package com.hindu.dgniryat.ui.otherscreen.fragments.BookArticle

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentBookArticleBinding
import java.io.IOException

private const val SMS_PERMISSION_REQUEST_CODE = 123
class BookArticle : Fragment() {

    private var _binding: FragmentBookArticleBinding? = null
    private val binding get(): FragmentBookArticleBinding = _binding!!

    //Spinner Data Update
    var selectedCountry = ""
    var selectedProductType = ""
    var selectedCategory = ""
    var selectedReturn = ""
    var selectedPbe = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookArticleBinding.inflate(inflater, container, false)

        binding.apply {

            //Select Country Spinner
            val countries = resources.getStringArray(R.array.Country)
            val selectCountryAdapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                countries
            )
            selectCountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            selectCountrySpinner.adapter = selectCountryAdapter

            selectCountrySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
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
            val productTypeAdapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                products
            )
            productTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            productTypeSpinner.adapter = productTypeAdapter

            productTypeSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
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
            val itemCategoryAdapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                items
            )
            itemCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            itemCategorySpinner.adapter = itemCategoryAdapter

            itemCategorySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
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
            val nonDeliveryAdapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                nondeliveries
            )
            nonDeliveryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            nonDeliverySpinner.adapter = nonDeliveryAdapter

            nonDeliverySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
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
            val selectPbeAdapter = ArrayAdapter(
                requireContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                Pbe
            )
            selectPbeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            selectPBESpinner.adapter = selectPbeAdapter

            selectPBESpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedItem = nondeliveries[p2]
                    selectedPbe = selectedItem
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


            proceedBtn1.setOnClickListener {
                llArticleDetails.visibility = View.GONE
                llReceiverDetails.visibility = View.VISIBLE
            }

            proceedBtn2.setOnClickListener {
                llReceiverDetails.visibility = View.GONE
                paymentCV.visibility = View.VISIBLE
                calculatePostage()
            }

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.SEND_SMS),
                    SMS_PERMISSION_REQUEST_CODE
                )

            } else {

                Toast.makeText(
                    requireContext(),
                    "Please grant SMS permission to continue",
                    Toast.LENGTH_SHORT
                ).show()

            }


            confirmPay.setOnClickListener {
                saveData(root)
            }

        }


        return binding.root
    }


    private fun saveData(view: View) {
        Snackbar.make(view, "Adding article....", Snackbar.LENGTH_SHORT).show()
        val dbRef = FirebaseDatabase.getInstance().reference.child("Consignments")
        val orderId = dbRef.push().key.toString()

        val orderMap = HashMap<String, Any>()
        orderMap["orderId"] = orderId
        orderMap["orderName"] = binding.articleName.text.toString()
        orderMap["rCountry"] = selectedCountry
        orderMap["productType"] = selectedProductType
        orderMap["itemCategory"] = selectedCategory
        orderMap["typeCount"] = binding.itemCount.text.toString()
        orderMap["grossWeight"] = binding.grossWeight.text.toString()
        orderMap["nonDelivery"] = selectedReturn
        orderMap["LicCont"] = binding.licCount.text.toString()
        orderMap["InvCount"] = binding.invoiceCount.text.toString()
        orderMap["certificateCount"] = binding.certificateCount.text.toString()
        orderMap["pbe"] = selectedPbe
        orderMap["publisher"] = FirebaseAuth.getInstance().currentUser!!.uid

        dbRef.child(orderId).updateChildren(orderMap)
        setStatus(orderId)
        receiverData(view, orderId)
        val message =
            "Dear user your Article is booked for $selectedCategory with ORDER NO. $orderId, Thank you for choosing Dakghar Niryat Kendra"
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.SEND_SMS),
                SMS_PERMISSION_REQUEST_CODE
            )

        } else {
            sendSms(message)

        }
        Navigation.findNavController(view).navigate(R.id.action_bookArticle_to_navigation_dashboard)
        Toast.makeText(context, "Payment Success & Article Added", Toast.LENGTH_SHORT).show()
    }

    private fun receiverData(view: View, orderId: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Address")

        val orderMap = HashMap<String, Any>()
        orderMap["receiverName"] = binding.receiverName.text.toString()
        orderMap["receiverAddress"] = "${binding.addressLine1.text} +${binding.addressLine2.text}"
        orderMap["country"] = selectedCountry
        orderMap["city"] = binding.city.text.toString()
        orderMap["rPhone"] = binding.receiverPhone.text.toString()
        orderMap["rEmail"] = binding.receiverEmail.text.toString()


        dbRef.child(orderId).updateChildren(orderMap)
        Snackbar.make(view, "Article added successfully", Snackbar.LENGTH_SHORT).show()

    }

    private fun setStatus(orderId: String) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Status")

        val statusMap = HashMap<String, Any>()
        statusMap["underProcess"] = 1
        statusMap["dispatchDakghar"] = 0
        statusMap["reachedExportWH"] = 0
        statusMap["exportCustom"] = 0
        statusMap["shipWarehouse"] = 0
        statusMap["importCustom"] = 0
        statusMap["importWarehouse"] = 0
        statusMap["regionalLogistics"] = 0
        statusMap["delivered"] = 0

        statusMap["message_dg"] = ""
        statusMap["message_ew"] = ""
        statusMap["message_ec"] = ""
        statusMap["message_sw"] = ""
        statusMap["message_ic"] = ""
        statusMap["message_iw"] = ""
        statusMap["message_rl"] = ""



        dbRef.child(orderId).updateChildren(statusMap)

    }

    private fun calculatePostage() {
        var cCharge = 0
        var totalCharge = 0
        var weightamount = binding.grossWeight.text.toString().toInt()
        var weightCharge = (weightamount / 1000) * 750
        var gst = 0


        if (selectedCountry == "USA") {
            cCharge = 50000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            binding.totalAmount.text = totalCharge.toString()
            binding.gstChargeAmount.text = gst.toString()
            binding.countryChargeAmount.text = cCharge.toString()

        }

        if (selectedCountry == "England") {
            cCharge = 30000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            binding.totalAmount.text = totalCharge.toString()
            binding.gstChargeAmount.text = gst.toString()
            binding.countryChargeAmount.text = cCharge.toString()
        }

        if (selectedCountry == "France") {
            cCharge = 45000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            binding.totalAmount.text = totalCharge.toString()
            binding.gstChargeAmount.text = gst.toString()
            binding.countryChargeAmount.text = cCharge.toString()
        }

        if (selectedCountry == "Russia") {
            cCharge = 20000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            binding.totalAmount.text = totalCharge.toString()
            binding.gstChargeAmount.text = gst.toString()
            binding.countryChargeAmount.text = cCharge.toString()
        }

        if (selectedCountry == "Japan") {
            cCharge = 25000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            binding.totalAmount.text = totalCharge.toString()
            binding.gstChargeAmount.text = gst.toString()
            binding.countryChargeAmount.text = cCharge.toString()
        }


    }

    private fun sendSms(message: String) {
        val smsManager = SmsManager.getDefault()
        val sentIntent = Intent("SMS_SENT")
        val deliveredIntent = Intent("SMS_DELIVERED")

        val sentPI =
            PendingIntent.getBroadcast(context, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE)
        val deliveredPI = PendingIntent.getBroadcast(
            context, 0, deliveredIntent,
            PendingIntent.FLAG_IMMUTABLE
        )


        try {
            smsManager
                .sendTextMessage(
                    "9118847494",
                    "7460802449",
                    message,
                    sentPI,
                    deliveredPI
                )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}