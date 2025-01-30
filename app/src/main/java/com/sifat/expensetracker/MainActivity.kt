package com.sifat.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sifat.expensetracker.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transactions: List<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactions = arrayListOf()

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        binding.recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
        fetchAll()

        binding.add.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchAll(){
        GlobalScope.launch {
            db.transactionDao().insertAll(Transaction(0,"Ice Cream", -3.0,"Yummy"))
            transactions = db.transactionDao().getAll()

            runOnUiThread {
                updateDashBoard()
                transactionAdapter.setData(transactions)
            }
        }
    }

    private fun updateDashBoard() {
        val totalAmount: Double = transactions.map { it.amount }.sum()
        val budgetAmount: Double = transactions.filter { it.amount > 0 }.map { it.amount }.sum()
        val expenseAmount: Double = totalAmount - budgetAmount

        binding.balance.text = "৳%.2f".format(totalAmount)
        binding.budget.text = "৳%.2f".format(budgetAmount)
        binding.expense.text = "৳%.2f".format(expenseAmount)
    }
}
