package com.hindu.dgniryat.Fragments.Invoice

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.findFragment
import com.hindu.dgniryat.Activity.Invoice
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
        val cv2 = root.findViewById<CardView>(R.id.inv_cv2)
        val cv3 = root.findViewById<CardView>(R.id.inv_cv3)

        productorde.setOnClickListener {
            startActivity(Intent(context,Invoice::class.java))
        }

        cv2.setOnClickListener {
            startActivity(Intent(context,Invoice::class.java))
        }

        cv3.setOnClickListener {
            startActivity(Intent(context,Invoice::class.java))
        }


        return root
    }


}