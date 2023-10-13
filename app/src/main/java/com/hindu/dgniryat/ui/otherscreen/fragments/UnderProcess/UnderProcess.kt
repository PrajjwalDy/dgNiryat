package com.hindu.dgniryat.ui.otherscreen.fragments.UnderProcess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hindu.dgniryat.databinding.FragmentUnderProcessBinding

class UnderProcess : Fragment() {

    private var _binding: FragmentUnderProcessBinding? = null
    private val binding get(): FragmentUnderProcessBinding = _binding!!

    private val viewModel: UnderProcessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnderProcessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}