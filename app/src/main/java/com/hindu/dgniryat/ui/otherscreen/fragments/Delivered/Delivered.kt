package com.hindu.dgniryat.ui.otherscreen.fragments.Delivered

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.databinding.FragmentDeliveredBinding

class Delivered : Fragment() {

    private var _binding: FragmentDeliveredBinding? = null
    private val binding get(): FragmentDeliveredBinding = _binding!!

    private val viewModel: DeliveredViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}