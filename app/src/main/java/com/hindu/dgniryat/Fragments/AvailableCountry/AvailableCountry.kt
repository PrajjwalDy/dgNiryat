package com.hindu.dgniryat.Fragments.AvailableCountry

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hindu.dgniryat.R

class AvailableCountry : Fragment() {

    companion object {
        fun newInstance() = AvailableCountry()
    }

    private lateinit var viewModel: AvailableCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_available_country, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AvailableCountryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}