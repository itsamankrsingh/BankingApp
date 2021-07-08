package com.itsamankrsingh.bankingapp.ui.fragment.successfulTransaction

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
import com.itsamankrsingh.bankingapp.databinding.FragmentSuccessfulTransactionBinding


class SuccessfulTransactionFragment : Fragment() {

    private lateinit var binding: FragmentSuccessfulTransactionBinding
    private lateinit var viewModel: SuccessfulTransactionViewModel

    private val args by navArgs<SuccessfulTransactionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuccessfulTransactionBinding.inflate(inflater)


        val transferAmount = args.transferAmount
        val senderCustomer = args.senderCustomer
        val receiverCustomer = args.receiverCustomer

        val application = requireNotNull(this.activity).application
        val databaseInstance = CustomerDatabase.getInstance(application)
        val customerDatasource = databaseInstance.customerDao
        val transactionRecordDatasource = databaseInstance.transactionRecordDao
        val viewModelFactory =
            SuccessfulTransactionViewModelFactory(customerDatasource, transactionRecordDatasource)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(SuccessfulTransactionViewModel::class.java)

        viewModel.initiateTransaction(
            requireContext(),
            senderCustomer,
            receiverCustomer,
            transferAmount
        )




        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner, {

            findNavController().navigate(R.id.action_successfulTransactionFragment_to_homeFragment)
        })

        viewModel.updatedSenderCustomer(senderCustomer)
        viewModel.receiverSenderCustomer(receiverCustomer)
        binding.transferredAmountTextView.text = transferAmount.toString()





        binding.lifecycleOwner = this
        binding.customerViewmodel = viewModel
        return binding.root
    }


}