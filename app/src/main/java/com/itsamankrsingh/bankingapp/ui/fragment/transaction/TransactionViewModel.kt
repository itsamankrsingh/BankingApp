package com.itsamankrsingh.bankingapp.ui.fragment.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsamankrsingh.bankingapp.database.Customer
import com.itsamankrsingh.bankingapp.database.CustomerDao
import kotlinx.coroutines.launch

class TransactionViewModel(private var databaseSource: CustomerDao) : ViewModel() {

    private var customerListId = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    var requiredIdList = ArrayList<Long>()

    private var _updatedCustomerList = MutableLiveData<List<Customer>>()
    var updatedCustomerList: LiveData<List<Customer>> = _updatedCustomerList

    init {


    }

    fun showCustomerList(){
        viewModelScope.launch {
            _updatedCustomerList.value = databaseSource.getCustomExcept(requiredIdList)


        }
    }



    fun updateCustomerList(customer: Customer) {

        for (id in customerListId) {
            if (id != customer.id) {
                requiredIdList.add(id)
            }
        }

    }

}