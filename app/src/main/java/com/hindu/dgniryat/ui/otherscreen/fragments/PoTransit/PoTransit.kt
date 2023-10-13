package com.hindu.dgniryat.ui.otherscreen.fragments.PoTransit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.databinding.FragmentPoTransitBinding

class PoTransit : Fragment() {

    private var _binding: FragmentPoTransitBinding? = null
    private val binding get(): FragmentPoTransitBinding = _binding!!

    private val viewModel: PoTransitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPoTransitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}