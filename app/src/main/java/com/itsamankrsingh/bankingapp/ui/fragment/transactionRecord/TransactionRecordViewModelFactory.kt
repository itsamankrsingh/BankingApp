package com.itsamankrsingh.bankingapp.ui.fragment.transactionRecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itsamankrsingh.bankingapp.database.TransactionRecordDao
import com.itsamankrsingh.bankingapp.ui.fragment.successfulTransaction.SuccessfulTransactionViewModel
import java.lang.IllegalArgumentException

class TransactionRecordViewModelFactory(private val transactionRecordDatasource: TransactionRecordDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionRecordViewModel::class.java)) {
            return TransactionRecordViewModel(
                transactionRecordDatasource
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}