package com.sifat.expensetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(private val transaction: ArrayList<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.label)
        val ammount: TextView = view.findViewById(R.id.ammount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction: Transaction = transaction[position]
        val context: Context = holder.ammount.context

        if (transaction.amount >= 0) {
            holder.ammount.text = "+ ৳%.2f".format(transaction.amount)
            holder.ammount.setTextColor(ContextCompat.getColor(context, R.color.Budgetecolor))
        } else {
            holder.ammount.text = "- ৳%.2f".format(Math.abs(transaction.amount))
            holder.ammount.setTextColor(ContextCompat.getColor(context, R.color.Expensecolor))
        }
        holder.label.text = transaction.level
    }

    override fun getItemCount(): Int {
        return transaction.size
    }
}
