package com.itsamankrsingh.bankingapp.ui.fragment.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itsamankrsingh.bankingapp.database.CustomerDatabase
import com.itsamankrsingh.bankingapp.databinding.FragmentCustomerBinding
import com.itsamankrsingh.bankingapp.ui.fragment.CustomerAdapter
import com.itsamankrsingh.bankingapp.ui.fragment.CustomerItemClickListener


class CustomerFragment : Fragment() {

    private lateinit var binding: FragmentCustomerBinding
    private lateinit var viewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomerBinding.inflate(inflater)


        val application = requireNotNull(this.activity).application
        val dataSource = CustomerDatabase.getInstance(application).customerDao
        val viewModelFactory = CustomerViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CustomerViewModel::class.java)

        val adapter = CustomerAdapter(CustomerItemClickListener { customer->
            val action = CustomerFragmentDirections.actionCustomerFragmentToDetailsFragment(customer)
            findNavController().navigate(action)
        })

        binding.customerRecyclerView.adapter = adapter

        viewModel.customerList.observe(viewLifecycleOwner, { customerList ->
            customerList.let {
                adapter.submitList(customerList)
            }

        })
        binding.viewmodel = viewModel
        return binding.root
    }


}