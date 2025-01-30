package com.sifat.expensetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(private var transaction: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView = view.findViewById(R.id.label)
        val amount: TextView = view.findViewById(R.id.ammount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.transaction, parent, false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction: Transaction = transaction[position]
        val context: Context = holder.amount.context

        if (transaction.amount >= 0) {
            holder.amount.text = "+ ৳%.2f".format(transaction.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.Budgetecolor))
        } else {
            holder.amount.text = "- ৳%.2f".format(Math.abs(transaction.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.Expensecolor))
        }
        holder.label.text = transaction.level
    }

    override fun getItemCount(): Int {
        return transaction.size
    }
    fun setData(transaction: List<Transaction>){
        this.transaction = transaction
        notifyDataSetChanged()
    }
}
