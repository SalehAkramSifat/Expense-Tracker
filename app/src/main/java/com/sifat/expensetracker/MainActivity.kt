package com.sifat.expensetracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sifat.expensetracker.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transaction: ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transaction = arrayListOf(
            Transaction("Weakend Budget", 400.00),
            Transaction("Bananas", -4.00),
            Transaction("Gasoline", -40.00)
        )

        transactionAdapter = TransactionAdapter(transaction)
        layoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        updateDashBoard()
    }

    private fun updateDashBoard(){
        val totalAmount: Double = transaction.map { it.amount }.sum()
        val budgetAmount:Double = transaction.filter { it.amount>0 }.map {it.amount}.sum()
        val expanceAmount:Double = totalAmount - budgetAmount

        binding.balance.text = "৳ %.2f".format(totalAmount)
        binding.budget.text = "৳ %.2f".format(budgetAmount)
        binding.expense.text = "৳ %.2f".format(expanceAmount)
    }
}