package com.hindu.dgniryat.ui.mainscreen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val bookArticle = root.findViewById<FloatingActionButton>(R.id.newArticle)
        val calculatePostage = root.findViewById<CardView>(R.id.postage)
        val availability = root.findViewById<CardView>(R.id.available_card)
        val popending = root.findViewById<CardView>(R.id.pending_card)
        val intransit = root.findViewById<CardView>(R.id.intransit_card)
        val underprocess = root.findViewById<CardView>(R.id.underprocess_card)
        val customs = root.findViewById<CardView>(R.id.custom_card)
        val customscleared = root.findViewById<CardView>(R.id.cleared_card)
        val deliveredcard = root.findViewById<CardView>(R.id.delivered_card)
        val invoice = root.findViewById<CardView>(R.id.invoiceCV)



        calculatePostage.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_postageCalculation)
        }

        bookArticle.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_bookArticle)
        }
        availability.setOnClickListener{
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_availableCountry)
        }
        popending.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_poTransit)
        }
        intransit.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_inTransit)
        }
        underprocess.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_underProcess)
        }
        customs.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_custom2)
        }
        customscleared.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_customCleared)
        }
        deliveredcard.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_delivered)
        }

        invoice.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_invoiceFragment2)
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}