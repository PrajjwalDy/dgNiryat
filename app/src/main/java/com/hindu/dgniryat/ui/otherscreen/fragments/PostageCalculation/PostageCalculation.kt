package com.hindu.dgniryat.ui.otherscreen.fragments.PostageCalculation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentPostageCalculationBinding

class PostageCalculation : Fragment() {

    var selectedCountry = ""

    private var _binding: FragmentPostageCalculationBinding? = null
    private val binding get(): FragmentPostageCalculationBinding = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostageCalculationBinding.inflate(inflater, container, false)

        binding.apply {

            //Select Country
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

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            calculateBtn.setOnClickListener {
                calculatePostage(resultCV)
                hideKeyboard()
                grossWeightEdt.text.clear()
                Toast.makeText(context, "Calculation Completed", Toast.LENGTH_SHORT).show()

            }

        }

        return binding.root
    }

    private fun hideKeyboard() {
        val imm = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    @SuppressLint("SetTextI18n")
    private fun calculatePostage(result: CardView) {
        var cCharge = 0
        var totalCharge = 0
        val weightamount = binding.grossWeightEdt.text.toString().toInt()
        val weightCharge = (weightamount / 1000) * 750
        var gst = 0

        binding.apply {

            if (selectedCountry == "USA") {
                cCharge = 50000
                var grossAmount = cCharge + weightCharge
                gst = grossAmount * 18 / 100
                totalCharge = gst + grossAmount

                estimatedCost.text = totalCharge.toString()
                grossWeightTv.text = "Gross Weight: ${grossWeightEdt.text} gms"
                gstTv.text = "GST: $gst"
                selectedCountryTv.text = "Country: $selectedCountry: $cCharge"
                estimatedCost.text = "Estimated Cost: $totalCharge"

            }

            if (selectedCountry == "England") {
                cCharge = 30000
                var grossAmount = cCharge + weightCharge
                gst = grossAmount * 18 / 100
                totalCharge = gst + grossAmount

                estimatedCost.text = totalCharge.toString()
                grossWeightTv.text = "Gross Weight: ${grossWeightEdt.text} gms"
                gstTv.text = "GST: $gst"
                selectedCountryTv.text = "Country: $selectedCountry: $cCharge"
                estimatedCost.text = "Estimated Cost: $totalCharge"
            }

            if (selectedCountry == "France") {
                cCharge = 45000
                var grossAmount = cCharge + weightCharge
                gst = grossAmount * 18 / 100
                totalCharge = gst + grossAmount

                estimatedCost.text = totalCharge.toString()
                grossWeightTv.text = "Gross Weight: ${grossWeightEdt.text} gms"
                gstTv.text = "GST: $gst"
                selectedCountryTv.text = "Country: $selectedCountry: $cCharge"
                estimatedCost.text = "Estimated Cost: $totalCharge"
            }

            if (selectedCountry == "Russia") {
                cCharge = 20000
                var grossAmount = cCharge + weightCharge
                gst = grossAmount * 18 / 100
                totalCharge = gst + grossAmount

                estimatedCost.text = totalCharge.toString()
                grossWeightTv.text = "Gross Weight: ${grossWeightEdt.text} gms"
                gstTv.text = "GST: $gst"
                selectedCountryTv.text = "Country: $selectedCountry: $cCharge"
                estimatedCost.text = "Estimated Cost: $totalCharge"
            }

            if (selectedCountry == "Japan") {
                cCharge = 25000
                var grossAmount = cCharge + weightCharge
                gst = grossAmount * 18 / 100
                totalCharge = gst + grossAmount

                estimatedCost.text = totalCharge.toString()
                grossWeightTv.text = "Gross Weight: ${grossWeightEdt.text} gms"
                gstTv.text = "GST: $gst"
                selectedCountryTv.text = "Country: $selectedCountry: $cCharge"
                estimatedCost.text = "Estimated Cost: $totalCharge"
            }

            result.visibility = View.VISIBLE

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}