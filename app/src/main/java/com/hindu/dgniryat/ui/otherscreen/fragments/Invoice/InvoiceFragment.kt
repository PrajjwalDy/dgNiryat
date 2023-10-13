package com.hindu.dgniryat.ui.otherscreen.fragments.Invoice

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.ui.otherscreen.activity.InvoiceActivity
import com.hindu.dgniryat.databinding.FragmentInvoiceBinding

class InvoiceFragment : Fragment() {

    private var _binding: FragmentInvoiceBinding? = null
    private val binding get(): FragmentInvoiceBinding = _binding!!

    private val viewModel: InvoiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvoiceBinding.inflate(inflater, container, false)

        binding.apply {

            productCard.setOnClickListener {
                startActivity(Intent(context, InvoiceActivity::class.java))
            }

            invCv2.setOnClickListener {
                startActivity(Intent(context, InvoiceActivity::class.java))
            }

            invCv3.setOnClickListener {
                startActivity(Intent(context, InvoiceActivity::class.java))
            }

        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}