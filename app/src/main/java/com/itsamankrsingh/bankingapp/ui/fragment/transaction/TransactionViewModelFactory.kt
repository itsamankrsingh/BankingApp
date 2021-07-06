package com.itsamankrsingh.bankingapp.ui.fragment.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itsamankrsingh.bankingapp.database.CustomerDao
import java.lang.IllegalArgumentException

class TransactionViewModelFactory(private var databaseSource: CustomerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            return TransactionViewModel(databaseSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}