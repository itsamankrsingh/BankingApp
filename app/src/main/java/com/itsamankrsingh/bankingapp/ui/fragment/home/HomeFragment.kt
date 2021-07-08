package com.itsamankrsingh.bankingapp.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itsamankrsingh.bankingapp.R
import com.itsamankrsingh.bankingapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater)

        binding.customerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_customerFragment)
        }

        binding.transactionButton.setOnClickListener {
           findNavController().navigate(R.id.action_homeFragment_to_transactionRecordFragment)
        }

        return binding.root
    }



}