package com.itsamankrsingh.bankingapp.ui.fragment.transactionRecord

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransitionImpl
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itsamankrsingh.bankingapp.R
import com.itsamankrsingh.bankingapp.database.CustomerDatabase
import com.itsamankrsingh.bankingapp.database.TransactionRecord
import com.itsamankrsingh.bankingapp.databinding.FragmentTransactionBinding
import com.itsamankrsingh.bankingapp.databinding.FragmentTransactionRecordBinding


class TransactionRecordFragment : Fragment() {

    private lateinit var binding: FragmentTransactionRecordBinding

    private lateinit var viewModel: TransactionRecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionRecordBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource = CustomerDatabase.getInstance(application).transactionRecordDao
        val viewModelFactory = TransactionRecordViewModelFactory(dataSource)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TransactionRecordViewModel::class.java)

        val adapter = TransactionRecordAdapter(TransactionRecordClickListener { transactionRecord ->
            viewModel.openDeleteTransactionRecordDialogBox(requireContext(), transactionRecord)
        })

        binding.transactionRecordRecyclerView.adapter = adapter

        viewModel.transactionRecordList.observe(
            viewLifecycleOwner,
            Observer { transactionRecordList ->
                adapter.submitList(transactionRecordList)
            })

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_all){
            viewModel.deleteAllTransaction(requireContext())
        }
        return false
    }
}