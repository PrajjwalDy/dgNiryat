package com.hindu.dgniryat.ui.mainscreen.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hindu.dgniryat.adapter.ConsignmentAdapter
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    var recyclerView:RecyclerView? = null
    private var consignmentAdapter:ConsignmentAdapter? =null
    private lateinit var dashboardViewModel: DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.articleModel!!.observe(viewLifecycleOwner, Observer {
            initView(root)
            consignmentAdapter = context?.let { it1-> ConsignmentAdapter(it1,it) }
            recyclerView!!.adapter = consignmentAdapter
        })
        return root
    }

    private fun initView(root: View){
        recyclerView = root.findViewById(R.id.consignmentRV) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView!!.layoutManager = linearLayoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}