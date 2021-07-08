package com.itsamankrsingh.bankingapp.ui.fragment.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itsamankrsingh.bankingapp.R
import com.itsamankrsingh.bankingapp.database.CustomerDatabase
import com.itsamankrsingh.bankingapp.databinding.FragmentTransactionBinding
import com.itsamankrsingh.bankingapp.ui.fragment.customer.CustomerAdapter
import com.itsamankrsingh.bankingapp.ui.fragment.customer.CustomerItemClickListener


class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding

    private lateinit var viewModel: TransactionViewModel

    private val args by navArgs<TransactionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(inflater)

        val amount = args.transferAmount
        val senderCustomer = args.customer

        val application = requireNotNull(this.activity).application
        val dataSource = CustomerDatabase.getInstance(application).customerDao
        val viewModelFactory = TransactionViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)
        viewModel.updateCustomerList(senderCustomer)

        viewModel.showCustomerList()

        val adapter = CustomerAdapter(CustomerItemClickListener { receiverCustomer ->
            val action =
                TransactionFragmentDirections.actionTransactionFragmentToSuccessfulTransactionFragment(
                    senderCustomer,
                    receiverCustomer,
                    amount
                )
            findNavController().navigate(action)
        })
        binding.transactionRecyclerView.adapter = adapter

        viewModel.updatedCustomerList.observe(viewLifecycleOwner, { customerList ->
            adapter.submitList(customerList)
        })

        return binding.root
    }


}