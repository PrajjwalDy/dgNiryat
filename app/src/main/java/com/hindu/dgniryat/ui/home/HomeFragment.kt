package com.hindu.dgniryat.ui.home

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

        calculatePostage.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_postageCalculation)
        }

        bookArticle.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_bookArticle)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}