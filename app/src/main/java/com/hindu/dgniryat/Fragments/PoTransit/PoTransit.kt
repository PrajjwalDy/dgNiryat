package com.hindu.dgniryat.Fragments.PoTransit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hindu.dgniryat.R

class PoTransit : Fragment() {

    companion object {
        fun newInstance() = PoTransit()
    }

    private lateinit var viewModel: PoTransitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_po_transit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PoTransitViewModel::class.java)
        // TODO: Use the ViewModel
    }

}