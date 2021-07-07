package com.itsamankrsingh.bankingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table")
    fun getAllCustomer(): LiveData<List<Customer>>

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table WHERE customer_id IN (:idArray)")
    suspend fun getCustomExcept(idArray: List<Long>): List<Customer>

    @Query("SELECT * FROM customer_table WHERE customer_id = (:customerId) ")
    suspend fun getCustomerById(customerId:Long):Customer

}