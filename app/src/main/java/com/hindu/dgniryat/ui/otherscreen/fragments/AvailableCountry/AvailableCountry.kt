package com.hindu.dgniryat.ui.otherscreen.fragments.AvailableCountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.databinding.FragmentAvailableCountryBinding

class AvailableCountry : Fragment() {

    private var _binding: FragmentAvailableCountryBinding? = null
    private val binding get(): FragmentAvailableCountryBinding = _binding!!

    private val viewModel: AvailableCountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAvailableCountryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}