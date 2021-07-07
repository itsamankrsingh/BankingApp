package com.itsamankrsingh.bankingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer1
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer10
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer11
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer2
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer3
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer4
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer5
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer6
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer7
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer8
import com.itsamankrsingh.bankingapp.utils.CustomerDetails.Companion.customer9
import com.itsamankrsingh.bankingapp.utils.ioThread

@Database(entities = [Customer::class,TransactionRecord::class],version = 1,exportSchema = false)
abstract class CustomerDatabase:RoomDatabase() {

    abstract val customerDao: CustomerDao

    abstract val transactionRecordDao:TransactionRecordDao

    companion object {

        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getInstance(context: Context): CustomerDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CustomerDatabase::class.java,
            "customer_database",
        ).addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer1)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer2)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer3)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer4)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer5)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer6)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer7)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer8)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer9)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer10)
                }
                ioThread {
                    getInstance(context).customerDao.insertCustomer(customer11)
                }
            }
        })
            .fallbackToDestructiveMigration()
            .build()
    }
}