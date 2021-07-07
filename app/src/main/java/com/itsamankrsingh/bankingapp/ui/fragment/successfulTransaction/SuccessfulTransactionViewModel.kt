package com.itsamankrsingh.bankingapp.ui.fragment.successfulTransaction

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsamankrsingh.bankingapp.database.Customer
import com.itsamankrsingh.bankingapp.database.CustomerDao
import kotlinx.coroutines.launch

class SuccessfulTransactionViewModel(private val datasource: CustomerDao) : ViewModel() {

    private  var _senderCustomer= MutableLiveData<Customer>()
    var senderCustomer: LiveData<Customer> = _senderCustomer

    private  var _receiverCustomer= MutableLiveData<Customer>()
    var receiverCustomer: LiveData<Customer> = _receiverCustomer





    fun initiateTransaction(
        context: Context,
        senderCustomer: Customer,
        receiverCustomer: Customer,
        transferAmount: Int
    ) {
        if (senderCustomer.accountBalance > transferAmount) {

            val remainingAmount = senderCustomer.accountBalance - transferAmount
            val updatedAmount = senderCustomer.accountBalance + transferAmount

            val senderCustomerUpdate = Customer(
                senderCustomer.id,
                senderCustomer.customerName,
                senderCustomer.customerEmail,
                senderCustomer.customerMobileNumber,
                senderCustomer.customerAccountNumber,
                senderCustomer.ifscCode,
                remainingAmount
            )

            val receiverCustomerUpdate = Customer(
                receiverCustomer.id,
                receiverCustomer.customerName,
                receiverCustomer.customerEmail,
                receiverCustomer.customerMobileNumber,
                receiverCustomer.customerAccountNumber,
                receiverCustomer.ifscCode,
                updatedAmount
            )

            viewModelScope.launch {
                datasource.updateCustomer(senderCustomerUpdate)
            }

            viewModelScope.launch {
                datasource.updateCustomer(receiverCustomerUpdate)
            }

            Toast.makeText(context, "Transaction Successful", Toast.LENGTH_SHORT).show()
        }
    }

    fun updatedSenderCustomer(senderCustomer: Customer){
        viewModelScope.launch {
            _senderCustomer.value= datasource.getCustomerById(senderCustomer.id)
        }
    }

    fun receiverSenderCustomer(receiverCustomer: Customer){
        viewModelScope.launch {
            _receiverCustomer.value= datasource.getCustomerById(receiverCustomer.id)
        }
    }
}