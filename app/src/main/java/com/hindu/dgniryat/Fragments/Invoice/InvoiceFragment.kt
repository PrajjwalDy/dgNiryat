package com.hindu.dgniryat.Fragments.Invoice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.hindu.dgniryat.R

class InvoiceFragment : Fragment() {


    private lateinit var viewModel: InvoiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(
            R.layout.fragment_invoice, container, false
        )

        val productorde = root.findViewById<CardView>(R.id.productord)

        productorde.setOnClickListener {

        }


        return root
    }


}