package com.hindu.dgniryat.ui.mainscreen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {

            bookArticle.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_bookArticle)
            }

            calculatePostage.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_postageCalculation)
            }

            availableCard.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_availableCountry)
            }

            poPending.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_poTransit)
            }

            inTransit.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_inTransit)
            }

            underProcess.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_underProcess)
            }

            customs.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_custom2)
            }

            customsCleared.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_customCleared)
            }

            delivered.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_delivered)
            }

            invoice.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_navigation_home_to_invoiceFragment2)
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}