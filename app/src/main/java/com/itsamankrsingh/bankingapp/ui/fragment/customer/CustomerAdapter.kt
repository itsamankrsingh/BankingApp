package com.itsamankrsingh.bankingapp.ui.fragment.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itsamankrsingh.bankingapp.database.Customer
import com.itsamankrsingh.bankingapp.databinding.CustomerItemBinding

class CustomerAdapter(val clickListener: CustomerItemClickListener) :
    ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(DiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(
            CustomerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CustomerViewHolder(private var binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: Customer, clickListener: CustomerItemClickListener) {
            binding.customer = customer
            binding.clicklistener = clickListener
            binding.executePendingBindings()
        }
    }


}

class CustomerItemClickListener(val clickListener: (customer: Customer) -> Unit) {
    fun onClick(customer: Customer) = clickListener(customer)
}