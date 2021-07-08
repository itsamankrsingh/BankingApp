package com.itsamankrsingh.bankingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_record")
data class TransactionRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    var transactionId: Long = 0L,
    val senderCustomerName: String,
    val receiverCustomerName: String,
    val senderAccountNumber: String,
    val receiverAccountNumber: String,
    val transferredAmount: Int,
    val status:Boolean
)