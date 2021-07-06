package com.itsamankrsingh.bankingapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "customer_table")
data class Customer constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    var id: Long = 0L,
    val customerName: String,
    val customerEmail: String,
    val customerMobileNumber: String,
    val customerAccountNumber: String,
    val ifscCode: String,
    val accountBalance: Int
) : Parcelable