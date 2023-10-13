package com.hindu.dgniryat.ui.otherscreen.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.hindu.dgniryat.R

class PostageCalculation : Fragment() {

    var selectedCountry = ""

    private lateinit var weight: EditText
    private lateinit var country: TextView

    private lateinit var grossWeight: TextView
    private lateinit var gstAmount: TextView
    private lateinit var estimatedCost: TextView

    private lateinit var calculate: AppCompatButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_postage_calculation, container, false)


        val selectCountry = root.findViewById<Spinner>(R.id.selectCountry_cp)
        weight = root.findViewById(R.id.grossWeight_cp)
        country = root.findViewById(R.id.selectedContry_cp)
        grossWeight = root.findViewById(R.id.grossWeright_cp)
        gstAmount = root.findViewById(R.id.gst_cp)
        estimatedCost = root.findViewById(R.id.estimatedCost)
        val resultCV = root.findViewById<CardView>(R.id.resultCV)


        calculate = root.findViewById(R.id.calculator)


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

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        calculate.setOnClickListener {
            calculatePostage(resultCV)
            hideKeyboard()
            weight.text.clear()
            Toast.makeText(context,"Calculation Completed",Toast.LENGTH_SHORT).show()

        }

        return root
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    private fun calculatePostage(result:CardView) {
        var cCharge = 0
        var totalCharge = 0
        var weightamount = weight.text.toString().toInt()
        var weightCharge = (weightamount / 1000) * 750
        var gst = 0


        if (selectedCountry == "USA") {
            cCharge = 50000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            estimatedCost.text = totalCharge.toString()
            grossWeight.text = "Gross Weight: " + weight.text + "gms"
            gstAmount.text = "GST: " + gst.toString()
            country.text = "Country: $selectedCountry: " + cCharge.toString()
            estimatedCost.text = "Estimated Cost: " + totalCharge.toString()

        }

        if (selectedCountry == "England") {
            cCharge = 30000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            estimatedCost.text = totalCharge.toString()
            grossWeight.text = "Gross Weight: " + weight.text + "gms"
            gstAmount.text = "GST: " + gst.toString()
            country.text = "Country: $selectedCountry: " + cCharge.toString()
            estimatedCost.text = "Estimated Cost: " + totalCharge.toString()
        }

        if (selectedCountry == "France") {
            cCharge = 45000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            estimatedCost.text = totalCharge.toString()
            grossWeight.text = "Gross Weight: " + weight.text + "gms"
            gstAmount.text = "GST: " + gst.toString()
            country.text = "Country: $selectedCountry: " + cCharge.toString()
            estimatedCost.text = "Estimated Cost: " + totalCharge.toString()
        }

        if (selectedCountry == "Russia") {
            cCharge = 20000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            estimatedCost.text = totalCharge.toString()
            grossWeight.text = "Gross Weight: " + weight.text + "gms"
            gstAmount.text = "GST: " + gst.toString()
            country.text = "Country: $selectedCountry: " + cCharge.toString()
            estimatedCost.text = "Estimated Cost: " + totalCharge.toString()
        }

        if (selectedCountry == "Japan") {
            cCharge = 25000
            var grossAmount = cCharge + weightCharge
            gst = grossAmount * 18 / 100
            totalCharge = gst + grossAmount

            estimatedCost.text = totalCharge.toString()
            grossWeight.text = "Gross Weight: " + weight.text + "gms"
            gstAmount.text = "GST: " + gst.toString()
            country.text = "Country: $selectedCountry: " + cCharge.toString()
            estimatedCost.text = "Estimated Cost: " + totalCharge.toString()
        }

        result.visibility = View.VISIBLE


    }

}