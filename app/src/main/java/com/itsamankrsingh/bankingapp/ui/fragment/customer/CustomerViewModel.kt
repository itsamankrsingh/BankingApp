package com.itsamankrsingh.bankingapp.ui.fragment.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsamankrsingh.bankingapp.database.Customer
import com.itsamankrsingh.bankingapp.database.CustomerDao
import kotlinx.coroutines.launch

class CustomerViewModel(private val datasource: CustomerDao):ViewModel() {

    lateinit var customerList: LiveData<List<Customer>>

    init {
        getCustomerList()
    }

    private fun getCustomerList() {
        viewModelScope.launch {
            customerList=datasource.getAllCustomer()
        }
    }

}