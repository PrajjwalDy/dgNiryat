package com.hindu.dgniryat.ui.otherscreen.fragments.CustomCleared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.databinding.FragmentCustomClearedBinding

class CustomCleared : Fragment() {

    private var _binding: FragmentCustomClearedBinding? = null
    private val binding get(): FragmentCustomClearedBinding = _binding!!

    private val viewModel: CustomClearedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomClearedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}